package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;
import android.webkit.WebView;
import android.widget.VideoView;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sautils.SAUtils;

public class SAMoatModule {

    private static final String kMoatClass = "tv.superawesome.lib.samoatevents.SAMoatEvents";

    // boolean mostly used for tests, in order to not limit moat at all
    private boolean   moatLimiting = true;

    // a moat object
    private Class<?>  moatClass = null;
    private Object    moatInstance = null;

    // the ad object
    private SAAd      ad;

    public SAMoatModule (Activity activity, SAAd ad) {

        // save the ad
        this.ad = ad;

        // create the moat class
        if (SAUtils.isClassAvailable(kMoatClass)) try {

            moatClass = Class.forName(kMoatClass);
            Constructor<?> moatConstructor = moatClass.getConstructor(Activity.class);
            moatInstance = moatConstructor.newInstance(activity);

        } catch (Exception e) {
            Log.w("SuperAwesome", "Could not create Moat instance because " + e.getMessage());
        }
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
        return ad != null && ((moatRand < ad.moat && moatLimiting) || !moatLimiting);
    }

    /**
     * Method that registers a Moat event object, according to the moat specifications
     *
     * @param view      the web view used by Moat to register events on (and that will contain
     *                  an ad at runtime)
     * @return          returns a MOAT specific string that will need to be inserted in the
     *                  web view so that the JS moat stuff works
     */
    public String startMoatTrackingForDisplay(WebView view) {

        if (moatInstance != null && isMoatAllowed()) try {

            HashMap<String, String> adData = new HashMap<>();
            adData.put("advertiserId", "" + ad.advertiserId);
            adData.put("campaignId", "" + ad.campaignId);
            adData.put("lineItemId", "" + ad.lineItemId);
            adData.put("creativeId", "" + ad.creative.id);
            adData.put("app", "" + ad.appId);
            adData.put("placementId", "" + ad.placementId);
            adData.put("publisherId", "" + ad.publisherId);

            java.lang.reflect.Method method = moatClass.getMethod("startMoatTrackingForDisplay", WebView.class, HashMap.class);
            Object returnValue = method.invoke(moatInstance, view, adData);
            return (String) returnValue;

        } catch (Exception e) {
            return "";
        } else {
            return "";
        }
    }

    /**
     * Unregister moat events for Display
     *
     * @return whether the removal was successful or not
     */
    public boolean stopMoatTrackingForDisplay() {

        if (moatInstance != null && isMoatAllowed()) try {

            java.lang.reflect.Method method = moatClass.getMethod("stopMoatTrackingForDisplay");
            Object returnValue = method.invoke(moatInstance);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Method that registers a Video Moat event
     *
     * @param video     the current Video View needed by Moat to do video tracking
     * @param mp        the current MediaPlayer associated with the video view
     * @return          whether the video moat event started OK
     */
    public boolean startMoatTrackingForVideoPlayer(VideoView video, MediaPlayer mp){

        if (moatInstance != null && isMoatAllowed()) try {

            HashMap<String, String> adData = new HashMap<>();
            adData.put("advertiserId", "" + ad.advertiserId);
            adData.put("campaignId", "" + ad.campaignId);
            adData.put("lineItemId", "" + ad.lineItemId);
            adData.put("creativeId", "" + ad.creative.id);
            adData.put("app", "" + ad.appId);
            adData.put("placementId", "" + ad.placementId);
            adData.put("publisherId", "" + ad.publisherId);

            java.lang.reflect.Method method = moatClass.getMethod("startMoatTrackingForVideoPlayer", VideoView.class, MediaPlayer.class, HashMap.class);
            Object returnValue = method.invoke(moatInstance, video, mp, adData);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Method to unregister a Moat event for video
     *
     * @return whether the video moat event was killed off OK
     */
    public boolean stopMoatTrackingForVideoPlayer() {

        if (moatInstance != null && isMoatAllowed()) try {

            java.lang.reflect.Method method = moatClass.getMethod("stopMoatTrackingForVideoPlayer");
            Object returnValue = method.invoke(moatInstance);
            return (Boolean) returnValue;

        } catch (Exception e) {
            return false;
        } else {
            return false;
        }
    }

    public void disableMoatLimiting () {
        moatLimiting = false;
    }
}
