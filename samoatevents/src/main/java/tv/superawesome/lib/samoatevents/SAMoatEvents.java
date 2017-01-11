/**
 * @Copyright:   SuperAwesome Trading Limited 2017
 * @Author:      Gabriel Coman (gabriel.coman@superawesome.tv)
 */
package tv.superawesome.lib.samoatevents;

import android.app.Activity;
import android.media.MediaPlayer;
import android.webkit.WebView;
import android.widget.VideoView;

import com.moat.analytics.mobile.MoatAdEvent;
import com.moat.analytics.mobile.MoatAdEventType;
import com.moat.analytics.mobile.MoatFactory;
import com.moat.analytics.mobile.NativeVideoTracker;
import com.moat.analytics.mobile.WebAdTracker;

import java.util.HashMap;

/**
 * Class that abstracts away the whole process of adding Moat tracking to ads
 */
public class SAMoatEvents {

    // Moat tracking hardcoded constants
    private static final String MOAT_SERVER                 = "https://z.moatads.com";
    private static final String MOAT_URL                    = "moatad.js";
    private static final String MOAT_DISPLAY_PARTNER_CODE   = "superawesomeinappdisplay731223424656";
    private static final String MOAT_VIDEO_PARTNER_CODE     = "superawesomeinappvideo467548716573";

    // Moat factory
    private MoatFactory factory = null;

    // hash maps containing the moat web tracker and native video trackers
    private HashMap<Integer, WebAdTracker> displayDict = new HashMap<>();
    private HashMap<Integer, NativeVideoTracker> videoDict = new HashMap<>();

    // the singleton SuperAwesome instance
    private static SAMoatEvents instance = new SAMoatEvents();

    /**
     * Method to return the only singleton existing method
     *
     * @return the current existing instance
     */
    public static SAMoatEvents getInstance(){
        return instance;
    }

    /**
     * Method that takes a view and some details and starts the Moat tracking process
     *
     * @param activity      the current activity
     * @param view          the WebView to register the moat event for
     * @param key           the unique tracker key (usually the placement Id)
     * @param adDetails     ad details (placement id, campaign id, etc)
     * @return              a string containing the proper Moat javascript code to execute in the
     *                      web view, or an empty string if there was an error
     */
    public String registerDisplayMoatEvent(Activity activity, WebView view, int key, HashMap<String, String> adDetails) {

        // create the Moat factory for each activity
        factory = MoatFactory.create(activity);

        // create a web ad tracker for the view
        WebAdTracker webAdTracker = factory.createWebAdTracker(view);

        // if could not create this, return empty string
        if (webAdTracker == null) return "";

        // form the proper moat data
        String moatQuery = "";
        moatQuery += "moatClientLevel1=" + adDetails.get("advertiserId");
        moatQuery += "&moatClientLevel2=" + adDetails.get("campaignId");
        moatQuery += "&moatClientLevel3=" + adDetails.get("lineItemId");
        moatQuery += "&moatClientLevel4=" + adDetails.get("creativeId");
        moatQuery += "&moatClientSlicer1=" + adDetails.get("app");
        moatQuery += "&moatClientSlicer2=" + adDetails.get("placementId");
        moatQuery += "&moatClientSlicer3=" + adDetails.get("publisherId");

        // and add a reference in the dictionary
        addWebTrackerToDict(key, webAdTracker);

        // start tracking
        webAdTracker.track();

        // and return the special moat javascript tag to be loaded in a web view
        return "<script src=\""+MOAT_SERVER+"/"+MOAT_DISPLAY_PARTNER_CODE+"/"+MOAT_URL+"?"+moatQuery+"\" type=\"text/javascript\"></script>";
    }

    /**
     * Method that unregisters a webAdTracker from the singleton, if it exists
     *
     * @param key   a unique tracker key, usually a placement id
     * @return      whether the operation was successful or not
     */
    public boolean unregisterDisplayMoatEvent (int key) {
        if (isWebTrackerInDict(key)) {
            removeWebTrackerFromDict(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actual method that adds web trackers to a dictionary
     *
     * @param key       unique key (placement id)
     * @param tracker   tracker object
     */
    private void addWebTrackerToDict (int key, WebAdTracker tracker) {
        displayDict.put(key, tracker);
    }

    /**
     * Actual method that removes web trackers from a dictionary
     *
     * @param key   unique key (placement id)
     */
    private void removeWebTrackerFromDict (int key) {
        displayDict.remove(key);
    }

    /**
     * Method usually used for testing that returns whether a certain tracker exists for a key
     *
     * @param key   unique key (placement id)
     * @return      true or false
     */
    public boolean isWebTrackerInDict (int key) {
        return displayDict.containsKey(key);
    }

    /**
     * Method that registers a new native video tracker and starts tracking the video ad
     *
     * @param video     the video view
     * @param mp        the media player
     * @param key       the unique key (placement Id)
     * @param adDetails ad data to send
     * @return          true or false, depending if the tracker is OK
     */
    public boolean registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp, int key, HashMap<String, String> adDetails){

        // create the factory
        factory = MoatFactory.create(activity);

        // create the native video tracker
        NativeVideoTracker nativeVideoTracker = factory.createNativeVideoTracker(MOAT_VIDEO_PARTNER_CODE);

        // defend against this
        if (nativeVideoTracker == null) return false;

        // track data
        HashMap<String, String> adIds = new HashMap<>();
        adIds.put("level1", "" + adDetails.get("advertiserId"));
        adIds.put("level2", "" + adDetails.get("campaignId"));
        adIds.put("level3", "" + adDetails.get("lineItemId"));
        adIds.put("level4", "" + adDetails.get("creativeId"));
        adIds.put("slicer1", "" + adDetails.get("app"));
        adIds.put("slicer2", "" + adDetails.get("placementId"));
        adIds.put("slicer3", "" + adDetails.get("publisherId"));

        // add native tracker to dict
        addVideoTrackerToDict(key, nativeVideoTracker);

        // begin tracking
        return nativeVideoTracker.trackVideoAd(adIds, mp, video);
    }

    /**
     * Method that unregisters a video moat event
     *
     * @param key unique key (placement id)
     * @return    true or false
     */
    public boolean unregisterVideoMoatEvent(int key){

        if (isVideoTrackerInDict(key)) {
            // get the tracker
            NativeVideoTracker tracker = videoDict.get(key);

            // create a "complete" event
            MoatAdEvent completeEvent = new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE);

            // dispatch the event
            tracker.dispatchEvent(completeEvent);

            // then remove the tracker altogether
            removeVideoTrackerFromDict(key);

            // return op successful
            return true;
        }
        else {
            // return op false
            return false;
        }
    }

    /**
     * Actual method that adds video trackers to a dictionary
     *
     * @param key       unique key (placement id)
     * @param tracker   tracker object
     */
    private void addVideoTrackerToDict (int key, NativeVideoTracker tracker) {
        videoDict.put(key, tracker);
    }

    /**
     * Actual method that removes video trackers from a dictionary
     *
     * @param key   unique key (placement id)
     */
    private void removeVideoTrackerFromDict (int key) {
        videoDict.remove(key);
    }

    /**
     * Method usually used for testing that returns whether a certain tracker exists for a key
     *
     * @param key   unique key (placement id)
     * @return      true or false
     */
    public boolean isVideoTrackerInDict (int key) {
        return videoDict.containsKey(key);
    }


}
