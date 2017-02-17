/**
 * @Copyright:   SuperAwesome Trading Limited 2017
 * @Author:      Gabriel Coman (gabriel.coman@superawesome.tv)
 */
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

import java.lang.reflect.Constructor;
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
 * Class that abstracts away the sending of events to the ad server. It also handles triggering
 * and disabling Moat Events
 */
public class SAEvents {

    // constants
    private final static short MAX_DISPLAY_TICKS = 1;
    private final static short MAX_VIDEO_TICKS   = 2;
    private final static int   DELAY             = 1000;

    // member variables (internal)
    private short     ticks        = 0;
    private short     check_tick   = 0;
    private Handler   handler      = null;
    private Runnable  runnable     = null;
    private Context   context      = null;

    // the ad that will be used to check for events and fire them
    private SAAd      refAd        = null;

    // an instance of SANetwork
    private SANetwork network      = new SANetwork();

    // boolean mostly used for tests, in order to not limit moat at all
    private boolean   moatLimiting = true;
    // a moat object
    private Object    moatInstance = null;

    /**
     * Basic constructor with a context
     *
     * @param context current context (activity or fragment)
     */
    public SAEvents (Context context) {
        this.context = context;
    }

    /**
     * Important setter that adds an ad to a SAEvents instance
     *
     * @param ad a new (hopefully valid) ad
     */
    public void setAd(SAAd ad) {
        refAd = ad;
    }

    /**
     * Method by which Moat can be fully enforced by disabling any limiting applied to it
     */
    public void disableMoatLimiting () {
        moatLimiting = false;
    }

    /**
     * Basic method to send events to an URL using the network object
     *
     * @param url       URL to send an event to
     * @param listener1 an instance of the SAEventsInterface to receive the answer on
     */
    public void sendEventToURL(final String url, SAEventsInterface listener1) {

        // create local listener
        final SAEventsInterface listener = listener1 != null ? listener1 : new SAEventsInterface() {@Override public void saDidGetEventResponse(boolean success, int status) {}};

        // create the header
        JSONObject header = SAJsonParser.newObject(new Object[]{
                "Content-Type", "application/json",
                "User-Agent", SAUtils.getUserAgent(context)
        });

        network.sendGET(context, url, new JSONObject(), header, new SANetworkInterface() {
            /**
             * Overridden SANetworkInterface method that sends back data after a network
             * request has been made
             *
             * @param status    status of the network request (200, 404, etc)
             * @param payload   return payload (a string)
             * @param success   general success status
             */
            @Override
            public void saDidGetResponse(int status, String payload, boolean success) {
                listener.saDidGetEventResponse(success, status);
            }
        });
    }

    /**
     * Shorthand send event method that has no callback
     *
     * @param url URL to send an event to
     */
    public void sendEventToURL(final String url) {
        sendEventToURL(url, null);
    }

    /**
     * Method that sends all events for a particular "key" in the "events" list of an ad
     *
     * @param key       the key to search events for in the "events" list
     * @param listener1 an instance of the SAEventsInterface to receive the answer on
     */
    public void sendEventsFor(String key, SAEventsInterface listener1) {

        // create local listener
        final SAEventsInterface listener = listener1 != null ? listener1 : new SAEventsInterface() {@Override public void saDidGetEventResponse(boolean success, int status) {}};

        // safety check
        if (refAd == null || key == null) {
            listener.saDidGetEventResponse(false, 0);
            return;
        }

        // add all events matching "key" to a new List
        List<String> urls = new ArrayList<>();
        for (SATracking event : refAd.creative.events) {
            if (event.event.equals(key)) {
                urls.add(event.URL);
            }
        }

        // vars needed to send events
        final int max = urls.size();
        final int[] successful = {0};
        final int[] current = {0};

        // finally send all events, one by one
        if (max > 0) {
            for (String url : urls) {
                sendEventToURL(url, new SAEventsInterface() {
                    @Override
                    public void saDidGetEventResponse(boolean success, int status) {
                        // increment
                        successful[0] += success ? 1 : 0;
                        current[0] += 1;

                        // once you reach the end of all events, just send one listener saDidGetEventResponse
                        // to the library user
                        if (current[0] == max) {
                            listener.saDidGetEventResponse(current[0] == successful[0], current[0] == successful[0] ? 200 : 0);
                        }
                    }
                });
            }
        }
        // in case of failure, just return the error listener
        else {
            listener.saDidGetEventResponse(false, 0);
        }
    }

    /**
     * Shorthand version of the previous method, without a listener
     *
     * @param key the key to search events for in the "events" list
     */
    public void sendEventsFor(String key) {
        sendEventsFor(key, null);
    }

    /**
     * Method that sends a viewable impression for a view. SuperAwesome calculates viewable
     * impression conditions for banner, interstitial, etc, ads using IAB standards
     *
     * @param child     the child view group
     * @param maxTicks  max ticks to check the view is visible on the screen before triggering the
     *                  viewable impression event
     * @param listener  an instance of the SAEventsInterface to receive the answer on
     */
    public void sendViewableImpressionForView(final ViewGroup child, final int maxTicks, final SAEventsInterface listener) {
        // safety check
        if (refAd == null || child == null) {
            if (listener != null) {
                listener.saDidGetEventResponse(false, 0);
            }
            return;
        }

        // create handler
        if (handler == null) {
            handler = new Handler();
        }

        // create a new runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                //
                // End: if this view has been visible for the number of ticks specified by the
                // method, then trigger the viewable impression
                if (ticks >= maxTicks) {
                    if (check_tick == maxTicks) {
                        sendEventsFor("superawesome_viewable_impression", listener);
                    } else {
                        if (listener != null) {
                            listener.saDidGetEventResponse(false, 0);
                        }
                    }
                }
                // In progress: else just continue ticking
                else {
                    ticks++;

                    // if the child becomes invalidated (e.g. view disappears from the screen
                    // while this runner works, then just kill it all and don't send a
                    // viewable impression)
                    if (child == null) {
                        if (listener != null) {
                            listener.saDidGetEventResponse(false, 0);
                        }
                        return;
                    }

                    // get the parent
                    View parent = (View) child.getParent();

                    // do one check to see if the parent is null - also useful if the
                    // view's parent disappears from the screen (and thus the view as well)
                    // if that's the case, just kill it all and don't send a viewable impression
                    if (parent == null) {
                        if (listener != null) {
                            listener.saDidGetEventResponse(false, 0);
                        }
                        return;
                    }

                    // now get the child position
                    int[] childPos = new int[2];
                    child.getLocationInWindow(childPos);
                    int childX = childPos[0];
                    int childY = childPos[1];
                    int childW = child.getWidth();
                    int childH = child.getHeight();
                    Rect childRect = new Rect(childX, childY, childW, childH);

                    // and the parent position
                    int[] parentPos = new int[2];
                    parent.getLocationInWindow(parentPos);
                    int parentX = parentPos[0];
                    int parentY = parentPos[1];
                    int parentW = parent.getWidth();
                    int parentH = parent.getHeight();
                    Rect parentRect = new Rect(parentX, parentY, parentW, parentH);

                    // and the whole screen position
                    Activity context = (Activity) child.getContext();
                    SAUtils.SASize screenSize = SAUtils.getRealScreenSize(context, false);
                    int screenX = 0;
                    int screenY = 0;
                    int screenW = screenSize.width;
                    int screenH = screenSize.height;
                    Rect screenRect = new Rect(screenX, screenY, screenW, screenH);

                    // if the child is in the parent and the child is also on screen,
                    // increment the counter that verifies for how long a view has been visible
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

    /**
     * Shorthand method to send a viewable impression for a Display ad
     *
     * @param layout the child view group
     */
    public void sendViewableImpressionForDisplay (ViewGroup layout) {
        sendViewableImpressionForView(layout, MAX_DISPLAY_TICKS, null);
    }

    /**
     * Shorthand method to send a viewable impression for a Video ad
     *
     * @param layout the child view group
     */
    public void sendViewableImpressionForVideo (ViewGroup layout) {
        sendViewableImpressionForView(layout, MAX_VIDEO_TICKS, null);
    }

    /**
     * Method that determines if Moat is allowed.
     * Conditions are:
     *  - that the ad should be not null
     *  - that moatLimiting should be enabled
     *  - if it's enabled, the random moat number should be smaller than
     *    the moat threshold of the ad
     *
     * @return true or false
     */
    public boolean isMoatAllowed () {
        // here calc if moat should be displayed
        int moatIntRand = SAUtils.randomNumberBetween(0, 100);
        double moatRand = moatIntRand / 100.0;
        return refAd != null && ((moatRand < refAd.moat && moatLimiting) || !moatLimiting);
    }

    /**
     * Method that registers a Moat event object, according to the moat specifications
     *
     * @param activity  the current activity
     * @param view      the web view used by Moat to register events on (and that will contain
     *                  an ad at runtime)
     * @return          returns a MOAT specific string that will need to be inserted in the
     *                  web view so that the JS moat stuff works
     */
    public String registerDisplayMoatEvent(Activity activity, WebView view) {

        if (SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents") && isMoatAllowed()) try {

            HashMap<String, String> adData = new HashMap<>();
            adData.put("advertiserId", "" + refAd.advertiserId);
            adData.put("campaignId", "" + refAd.campaignId);
            adData.put("lineItemId", "" + refAd.lineItemId);
            adData.put("creativeId", "" + refAd.creative.id);
            adData.put("app", "" + refAd.appId);
            adData.put("placementId", "" + refAd.placementId);
            adData.put("publisherId", "" + refAd.publisherId);

            Class<?> moatClass = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            Constructor<?> moatConstructor = moatClass.getConstructor();
            moatInstance = moatConstructor.newInstance();

            java.lang.reflect.Method method = moatClass.getMethod("registerDisplayMoatEvent", Activity.class, WebView.class, HashMap.class);
            Object returnValue = method.invoke(moatInstance, activity, view, adData);
            return (String) returnValue;

        } catch (Exception e) {
            return "";
        }

        return "";
    }

    /**
     * Unregister moat events for Display
     *
     * @return whether the removal was successful or not
     */
    public boolean unregisterDisplayMoatEvent() {

        if (SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents") && moatInstance != null) try {

            Class<?> moatClass = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moatClass.getMethod("unregisterDisplayMoatEvent");
            Object returnValue = method.invoke(moatInstance);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /**
     * Method that registers a Video Moat event
     *
     * @param activity  the current activity
     * @param video     the current Video View needed by Moat to do video tracking
     * @param mp        the current MediaPlayer associated with the video view
     * @return          whether the video moat event started OK
     */
    public boolean registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp){

        if (SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents") && isMoatAllowed()) try {

            HashMap<String, String> adData = new HashMap<>();
            adData.put("advertiserId", "" + refAd.advertiserId);
            adData.put("campaignId", "" + refAd.campaignId);
            adData.put("lineItemId", "" + refAd.lineItemId);
            adData.put("creativeId", "" + refAd.creative.id);
            adData.put("app", "" + refAd.appId);
            adData.put("placementId", "" + refAd.placementId);
            adData.put("publisherId", "" + refAd.publisherId);

            Class<?> moatClass = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            Constructor<?> moatConstructor = moatClass.getConstructor();
            moatInstance = moatConstructor.newInstance();

            java.lang.reflect.Method method = moatClass.getMethod("registerVideoMoatEvent", Activity.class, VideoView.class, MediaPlayer.class, HashMap.class);
            Object returnValue = method.invoke(moatInstance, activity, video, mp, adData);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /**
     * Method to unregister a Moat event for video
     *
     * @return whether the video moat event was killed off OK
     */
    public boolean unregisterVideoMoatEvent() {

        if (SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents") && moatInstance != null) try {

            Class<?> moatClass = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moatClass.getMethod("unregisterVideoMoatEvent");
            Object returnValue = method.invoke(moatInstance);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
