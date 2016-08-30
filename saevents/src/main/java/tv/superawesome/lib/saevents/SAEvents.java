package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.samodelspace.SATracking;
import tv.superawesome.lib.sanetwork.request.*;
import tv.superawesome.lib.sautils.SAUtils;
import tv.superawesome.lib.sautils.SAApplication;

/**
 * Class that sends events to the server (click, viewable impression, etc)
 */
public class SAEvents {

    // private consts
    private final static short MAX_TICKS = 2;
    private final static int DELAY = 1000;

    // private vars
    private short ticks = 0;
    private short check_tick = 0;
    private Handler handler = null;
    private Runnable runnable = null;

    // private vars w/ public inteface
    private SAAd refAd = null;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Init
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public SAEvents () {
        // empty init
        handler = new Handler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // setters & getters
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAd(SAAd ad) {
        refAd = ad;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // event functions
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void sendEventToURL(final String url) {

        SAUtils.SAConnectionType type = SAUtils.SAConnectionType.unknown;
        Context c = SAApplication.getSAApplicationContext();
        if (c != null) {
            type = SAUtils.getNetworkConnectivity(c);
        }
        // simple version for now
        String finalEvtUrl = url; // + "&ct=" + type.ordinal();

        // get the header
        JSONObject header = SAJsonParser.newObject(new Object[]{
                "Content-Type", "application/json",
                "User-Agent", SAUtils.getUserAgent()
        });

        SANetwork network = new SANetwork();
        network.sendGET(c, finalEvtUrl, new JSONObject(), header, new SANetworkInterface() {
            @Override
            public void response(int status, String payload, boolean success) {
                Log.d("SuperAwesome", "[" + success + "] Event response " + status + " | " + payload);
            }
        });
    }

    public void sendEventsFor(String key) {
        // safety check
        if (refAd == null) return;

        // send events
        List<String> urls = new ArrayList<>();
        for (SATracking event : refAd.creative.events) {
            if (event.event.equals(key)) {
                urls.add(event.URL);
            }
        }

        // send event
        for (String url : urls) {
            sendEventToURL(url);
        }
    }

    public void sendViewableForFullscreen () {
        // safety check
        if (refAd == null) return;

        // call runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                if (ticks >= MAX_TICKS) {
                    sendEventsFor("viewable_impr");
                } else {
                    ticks++;

                    // log
                    Log.d("SuperAwesome", "Viewability count " + ticks + "/" + MAX_TICKS);

                    // start again
                    handler.postDelayed(runnable, DELAY);
                }
            }
        };

        // start
        handler.postDelayed(runnable, DELAY);
    }

    public void sendViewableForInScreen (final RelativeLayout child) {
        // safety check
        if (refAd == null) return;

        // call runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                if (ticks > MAX_TICKS) {
                    if (check_tick == MAX_TICKS) {
                        sendEventsFor("viewable_impr");
                    } else {
                        Log.d("SuperAwesome", "Could not send viewable impression since it appears view is not on screen");
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
                    Log.d("SuperAwesome", "Viewability count " + ticks + "/" + MAX_TICKS);

                    // run again
                    handler.postDelayed(runnable, DELAY);
                }
            }
        };

        // start
        handler.postDelayed(runnable, DELAY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // possible Moat events
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String registerDisplayMoatEvent(Activity activity, WebView view, HashMap<String, String> adData) {
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return "";

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
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return;

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

    public void registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp, HashMap<String, String> adData){
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return;

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
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return;

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
