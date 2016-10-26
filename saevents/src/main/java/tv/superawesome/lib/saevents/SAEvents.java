package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.VideoView;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.samodelspace.SATracking;
import tv.superawesome.lib.sanetwork.request.SANetwork;
import tv.superawesome.lib.sanetwork.request.SANetworkInterface;
import tv.superawesome.lib.sautils.SAUtils;

/**
 * Class that sends events to the server (click, viewable impression, etc)
 */
public class SAEvents {

    // private constants
    private final static short MAX_DISPLAY_TICKS = 1;
    private final static short MAX_VIDEO_TICKS = 2;
    private final static int DELAY = 1000;

    // private vars
    private short ticks = 0;
    private short check_tick = 0;
    private Handler handler = null;
    private Runnable runnable = null;
    private Context context = null;

    // private vars w/ public inteface
    private SAAd refAd = null;
    SANetwork network = new SANetwork();
    private boolean moatLimiting = true;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Init
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public SAEvents (Context context) {
        // empty init
        handler = new Handler();
        this.context = context;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // setters & getters
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAd(SAAd ad) {
        refAd = ad;
    }

    public void disableMoatLimiting () {
        moatLimiting = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // event functions
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void sendEventToURL(final String url, final SAEventsInterface listener) {
        // get the header
        JSONObject header = SAJsonParser.newObject(new Object[]{
                "Content-Type", "application/json",
                "User-Agent", SAUtils.getUserAgent(context)
        });

        network.sendGET(context, url, new JSONObject(), header, new SANetworkInterface() {
            @Override
            public void response(int status, String payload, boolean success) {
                if (listener != null) {
                    listener.response(success, status);
                }
            }
        });
    }

    public void sendEventToURL(final String url) {
        sendEventToURL(url, null);
    }

    public void sendEventsFor(String key, final SAEventsInterface listener) {
        // safety check
        if (refAd == null || key == null) {
            if (listener != null) {
                listener.response(false, 0);
            }
        }

        // send events
        List<String> urls = new ArrayList<>();
        for (SATracking event : refAd.creative.events) {
            if (event.event.equals(key)) {
                urls.add(event.URL);
            }
        }

        final int max = urls.size();
        final int[] successful = {0};
        final int[] current = {0};

        if (max > 0) {
            // send event
            for (String url : urls) {
                sendEventToURL(url, new SAEventsInterface() {
                    @Override
                    public void response(boolean success, int status) {
                        // increment
                        successful[0] += success ? 1 : 0;
                        current[0] += 1;

                        // once you reach the end
                        if (current[0] == max && listener != null) {
                            listener.response(current[0] == successful[0], current[0] == successful[0] ? 200 : 0);
                        }
                    }
                });
            }
        }
        else {
            if (listener != null) {
                listener.response(false, 0);
            }
        }
    }

    public void sendEventsFor(String key) {
        sendEventsFor(key, null);
    }

    public void sendViewableImpressionForView(final ViewGroup child, final int maxTicks, final SAEventsInterface listener) {
        // safety check
        if (refAd == null || child == null) {
            if (listener != null) {
                listener.response(false, 0);
            }
            return;
        }

        // call runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                if (ticks >= maxTicks) {
                    if (check_tick == maxTicks) {
                        sendEventsFor("viewable_impr", listener);
                    } else {
                        if (listener != null) {
                            listener.response(false, 0);
                        }
                    }
                } else {
                    ticks++;

                    // child
                    int[] childPos = new int[2];
                    child.getLocationInWindow(childPos);
                    int childX = childPos[0];
                    int childY = childPos[1];
                    int childW = child.getWidth();
                    int childH = child.getHeight();
                    Rect childRect = new Rect(childX, childY, childW, childH);

                    // super
                    View parent = (View) child.getParent();
                    int[] parentPos = new int[2];
                    parent.getLocationInWindow(parentPos);
                    int parentX = parentPos[0];
                    int parentY = parentPos[1];
                    int parentW = parent.getWidth();
                    int parentH = parent.getHeight();
                    Rect parentRect = new Rect(parentX, parentY, parentW, parentH);

                    // screen
                    Activity context = (Activity) child.getContext();
                    SAUtils.SASize screenSize = SAUtils.getRealScreenSize(context, false);
                    int screenX = 0;
                    int screenY = 0;
                    int screenW = screenSize.width;
                    int screenH = screenSize.height;
                    Rect screenRect = new Rect(screenX, screenY, screenW, screenH);

                    if (SAUtils.isTargetRectInFrameRect(childRect, parentRect) && SAUtils.isTargetRectInFrameRect(childRect, screenRect)){
                        check_tick++;
                    }

                    // log
                    Log.d("SuperAwesome", "Viewability count " + ticks + "/" + maxTicks);

                    // run again
                    handler.postDelayed(runnable, DELAY);
                }
            }
        };

        // start
        handler.postDelayed(runnable, DELAY);
    }

    public void sendViewableImpressionForDisplay (ViewGroup layout) {
        sendViewableImpressionForView(layout, MAX_DISPLAY_TICKS, null);
    }

    public void sendViewableImpressionForVideo (ViewGroup layout) {
        sendViewableImpressionForView(layout, MAX_VIDEO_TICKS, null);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // possible Moat events
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String registerDisplayMoatEvent(Activity activity, WebView view) {
        // if Moat is not present then don't go forward
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) {
            return "";
        }

        // go forward when moat limiting is not enabled and only in 1 out of 5 cases
        if (moatLimiting && SAUtils.randomNumberBetween(0, 100) >= 80) {
            return "";
        }

        // get the actual data needed for moat
        HashMap<String, String> adData = new HashMap<>();
        adData.put("advertiserId", "" + refAd.advertiserId);
        adData.put("campaignId", "" + refAd.campaignId);
        adData.put("lineItemId", "" + refAd.lineItemId);
        adData.put("creativeId", "" + refAd.creative.id);
        adData.put("app", "" + refAd.app);
        adData.put("placementId", "" + refAd.placementId);
        adData.put("publisherId", "" + refAd.publisherId);

        try {
            Class<?> moat = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moat.getMethod("getInstance");
            Object moatInstance = method.invoke(moat);
            java.lang.reflect.Method method1 = moat.getMethod("registerDisplayMoatEvent", Activity.class, WebView.class, HashMap.class);
            Object returnValue = method1.invoke(moatInstance, activity, view, adData);
            return (String)returnValue;
        } catch (ClassNotFoundException e) {
            // failure
        } catch (NoSuchMethodException e) {
            // failure
        } catch (InvocationTargetException e) {
            // failure
        } catch (IllegalAccessException e) {
            // failure;
        }

        return "";
    }

    public void unregisterDisplayMoatEvent(int placementId) {
        // if Moat is not present then don't go forward
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) {
            return;
        }

        // go forward when moat limiting is not enabled and only in 1 out of 5 cases
        if (moatLimiting) {
            return;
        }

        try {
            Class<?> moat = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moat.getMethod("getInstance");
            Object moatInstance = method.invoke(moat);
            java.lang.reflect.Method method1 = moat.getMethod("unregisterDisplayMoatEvent", int.class);
            method1.invoke(moatInstance, placementId);
        } catch (ClassNotFoundException e) {
            // failure
        } catch (NoSuchMethodException e) {
            // failure
        } catch (InvocationTargetException e) {
            // failure
        } catch (IllegalAccessException e) {
            // failure;
        }
    }

    public void registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp){
        // if Moat is not present then don't go forward
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) {
            return;
        }

        // go forward when moat limiting is not enabled and only in 1 out of 5 cases
        if (moatLimiting && SAUtils.randomNumberBetween(0, 100) >= 80) {
            return;
        }

        // form the ad data hash map to send to moat
        HashMap<String, String> adData = new HashMap<>();
        adData.put("advertiserId", "" + refAd.advertiserId);
        adData.put("campaignId", "" + refAd.campaignId);
        adData.put("lineItemId", "" + refAd.lineItemId);
        adData.put("creativeId", "" + refAd.creative.id);
        adData.put("app", "" + refAd.app);
        adData.put("placementId", "" + refAd.placementId);
        adData.put("publisherId", "" + refAd.publisherId);

        try {
            Class<?> moat = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moat.getMethod("getInstance");
            Object moatInstance = method.invoke(moat);
            java.lang.reflect.Method method1 = moat.getMethod("registerVideoMoatEvent", Activity.class, VideoView.class, MediaPlayer.class, HashMap.class);
            method1.invoke(moatInstance, activity, video, mp, adData);
        } catch (ClassNotFoundException e) {
            // failure
        } catch (NoSuchMethodException e) {
            // failure
        } catch (InvocationTargetException e) {
            // failure
        } catch (IllegalAccessException e) {
            // failure;
        }
    }

    public void unregisterVideoMoatEvent(int placementId) {
        // if Moat is not present then don't go forward
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return;

        // go forward when moat limiting is not enabled and only in 1 out of 5 cases
        if (moatLimiting && SAUtils.randomNumberBetween(0, 100) >= 80) {
            return;
        }

        try {
            Class<?> moat = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moat.getMethod("getInstance");
            Object moatInstance = method.invoke(moat);
            java.lang.reflect.Method method1 = moat.getMethod("unregisterVideoMoatEvent", int.class);
            method1.invoke(moatInstance, placementId);
        } catch (ClassNotFoundException e) {
            // failure
        } catch (NoSuchMethodException e) {
            // failure
        } catch (InvocationTargetException e) {
            // failure
        } catch (IllegalAccessException e) {
            // failure;
        }
    }
}
