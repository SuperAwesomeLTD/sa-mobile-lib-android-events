package tv.superawesome.lib.samoatevents;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.moat.analytics.mobile.MoatFactory;
import com.moat.analytics.mobile.NativeDisplayTracker;
import com.moat.analytics.mobile.NativeVideoTracker;

import java.util.HashMap;
import java.util.Random;

/**
// * Created by gabriel.coman on 01/06/16.
 */
public class SAMoatEvents {

    /** Moat tracking hardcoded constants */
    private final String MOAT_DISPLAY_PARTNER_CODE = "superawesomeinappdisplay731223424656";
    private final String MOAT_VIDEO_PARTNER_CODE = "superawesomeinappvideo467548716573";

    /** other variables */
    private MoatFactory factory = null;
    private HashMap<String, NativeDisplayTracker> displayDict;
    private HashMap<String, NativeVideoTracker> videoDict;

    /** the singleton SuperAwesome instance */
    private static SAMoatEvents instance = new SAMoatEvents();

    /** make the constructor private so that this class cannot be instantiated */
    private SAMoatEvents(){
        displayDict = new HashMap<>();
        videoDict = new HashMap<>();
    }

    /** Get the only object available */
    public static SAMoatEvents getInstance(){
        return instance;
    }

    /**
     * Function that sends a moat tracking event
     * @param view - the parent view
     * @param adDetails - ad data to be sent
     */
    public void sendDisplayMoatEvent(Activity activity, View view, HashMap<String, String> adDetails) {

        Random rand  = new Random();
        if (rand.nextInt(101) > 20) {
            Log.d("SuperAwesome", "[AA :: Info] Did not send Moat Display Event this time");
            return;
        }

        /** create factory */
        factory = MoatFactory.create(activity);
        NativeDisplayTracker moatDisplayTracker = factory.createNativeDisplayTracker(view, MOAT_DISPLAY_PARTNER_CODE);

        /** track data */
        HashMap<String, String> adIds = new HashMap<String, String>();
        adIds.put("moatClientLevel1", "SuperAwesome");
        adIds.put("moatClientLevel2", "" + adDetails.get("campaignId"));
        adIds.put("moatClientLevel3", "" + adDetails.get("lineItemId"));
        adIds.put("moatClientLevel4", "" + adDetails.get("creativeId"));
        adIds.put("moatClientSlicer1", "" + adDetails.get("app"));
        adIds.put("moatClientSlicer2", "" + adDetails.get("placementId"));

        displayDict.put("display_ad_tracker_" + adDetails.get("placementId"), moatDisplayTracker);
        moatDisplayTracker.track(adIds);

        Log.d("SuperAwesome", "[AA :: Info] Register Moat Display Event");
    }

    public void sentDisplayMoatStop (String adId) {
        NativeDisplayTracker tracker = displayDict.get("display_ad_tracker_" + adId);
        if (tracker != null) {
            tracker.stopTracking();
        }
    }

    /**
     * Start sending video events
     * @param video - the video view
     * @param mp - the media player
     * @param adDetails - ad data to send
     */
    public void sendVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp, HashMap<String, String> adDetails){

        Random rand  = new Random();
        if (rand.nextInt(101) > 20) {
            Log.d("SuperAwesome", "[AA :: Info] Did not send Moat Video Event this time");
            return;
        }

        /** create video tracker object */
        factory = MoatFactory.create(activity);
        NativeVideoTracker moatVideoTracker = factory.createNativeVideoTracker(MOAT_VIDEO_PARTNER_CODE);

        /** track data */
        HashMap<String, String> adIds = new HashMap<String, String>();
        adIds.put("moatClientLevel1", "SuperAwesome");
        adIds.put("moatClientLevel2", "" + adDetails.get("campaignId"));
        adIds.put("moatClientLevel3", "" + adDetails.get("lineItemId"));
        adIds.put("moatClientLevel4", "" + adDetails.get("creativeId"));
        adIds.put("moatClientSlicer1", "" + adDetails.get("app"));
        adIds.put("moatClientSlicer2", "" + adDetails.get("placementId"));

        videoDict.put("video_ad_tracker_" + adDetails.get("placementId"), moatVideoTracker);
        moatVideoTracker.trackVideoAd(adIds, mp, video);

        Log.d("SuperAwesome", "[AA :: Info] Register Moat Video Event");
    }

    /**
     * Send the Video complete event and remove the tracker from the dict
     * @param adId
     */
    public void sendVideoMoatComplete(String adId){
        /** go on */
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("type", "AdVideoComplete");
        NativeVideoTracker tracker = videoDict.get("video_ad_tracker_" + adId);

        if (tracker != null) {
            Log.d("SuperAwesome", "[AA :: Info] Remove Moat Video Event");
            tracker.dispatchEvent(params);
            videoDict.remove("video_ad_tracker_" + adId);
        }
    }
}
