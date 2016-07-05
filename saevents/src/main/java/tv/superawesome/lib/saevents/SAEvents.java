package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import tv.superawesome.lib.sanetwork.request.*;
import tv.superawesome.lib.sautils.SAUtils;
import tv.superawesome.lib.sautils.SAApplication;

/**
 * Class that sends events to the server (click, viewable impression, etc)
 */
public class SAEvents {

    /**
     * Static functions
     */
    private static boolean isSATrackingEnabled = true;

    /**
     * Fire-and-forget event function
     *
     * @param url - the event url to send the data to
     */
    public static void sendEventToURL(final String url) {
        if (!isSATrackingEnabled) return;


        SAUtils.SAConnectionType type = SAUtils.SAConnectionType.unknown;
        Context c = SAApplication.getSAApplicationContext();
        if (c != null) {
            type = SAUtils.getNetworkConnectivity(c);
        }
        // simple version for now
        String finalEvtUrl = url; // + "&ct=" + type.ordinal();

        JSONObject header = new JSONObject();
        try {
            header.put("Content-Type", "application/json");
            header.put("User-Agent", SAUtils.getUserAgent());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SANetwork network = new SANetwork();
        network.sendGET(c, finalEvtUrl, new JSONObject(), header, new SANetworkInterface() {
            @Override
            public void success(int status, String payload) {
                Log.d("SuperAwesome", "Event response " + status + " | " + payload);
            }

            @Override
            public void failure() {

            }
        });
    }

    public static void enableSATracking() {
        isSATrackingEnabled = true;
    }

    public static void disableSATracking() {
        isSATrackingEnabled = false;
    }

    public static void registerDisplayMoatEvent(Activity activity, View view, HashMap<String, String> adData) {
        if (!SAUtils.isClassAvailable("tv.superawesome.lib.samoatevents.SAMoatEvents")) return;

        try {
            Class<?> moat = Class.forName("tv.superawesome.lib.samoatevents.SAMoatEvents");
            java.lang.reflect.Method method = moat.getMethod("getInstance");
            Object moatInstance = method.invoke(moat);
            java.lang.reflect.Method method1 = moat.getMethod("registerDisplayMoatEvent", Activity.class, View.class, HashMap.class);
            method1.invoke(moatInstance, activity, view, adData);
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

    public static void unregisterDisplayMoatEvent(int placementId) {
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

    public static void registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp, HashMap<String, String> adData){
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

    public static void unregisterVideoMoatEvent(int placementId) {
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
