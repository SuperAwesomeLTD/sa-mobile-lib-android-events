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

    private final String MOAT_DISPLAY_KEY = "display_ad_tracker_";
    private final String MOAT_VIDEO_KEY = "video_ad_tracker_";

    private final String MOAT_ERROR_MSG = "[AA :: Info | Moat] Did not send moat event this time";
    private final String MOAT_DISPLAY_REGISTER_MSG = "[AA :: Info | Moat ] Register display event for key: ";
    private final String MOAT_DISPLAY_UNREGISTER_MSG = "[AA :: Info | Moat] Stop tracking for key: ";
    private final String MOAT_VIDEO_REGISTER_MSG = "";
    private final String MOAT_VIDEO_UNREGISTER_MSG = "";

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
    public void registerDisplayMoatEvent(Activity activity, View view, HashMap<String, String> adDetails) {

        Random rand  = new Random();
        if (rand.nextInt(101) > 20) {
            Log.d("SuperAwesome", MOAT_ERROR_MSG);
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

        String key = MOAT_DISPLAY_KEY + adDetails.get("placementId");
        displayDict.put(key, moatDisplayTracker);
        moatDisplayTracker.track(adIds);

        Log.d("SuperAwesome", MOAT_DISPLAY_REGISTER_MSG + key);
    }

    /**
     * Unregister display moat events
     * @param placementId
     */
    public void unregisterDisplayMoatEvent (int placementId) {
        String key = MOAT_DISPLAY_KEY + placementId;
        NativeDisplayTracker tracker = displayDict.get(key);
        if (tracker != null) {
            Log.d("SuperAwesome", MOAT_DISPLAY_UNREGISTER_MSG + key);
            tracker.stopTracking();
        }
    }

    /**
     * Start sending video events
     * @param video - the video view
     * @param mp - the media player
     * @param adDetails - ad data to send
     */
    public void registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp, HashMap<String, String> adDetails){

        Random rand  = new Random();
        if (rand.nextInt(101) > 20) {
            Log.d("SuperAwesome", MOAT_ERROR_MSG);
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

        String key = MOAT_VIDEO_KEY + adDetails.get("placementId");
        videoDict.put(key, moatVideoTracker);
        moatVideoTracker.trackVideoAd(adIds, mp, video);

        Log.d("SuperAwesome", MOAT_VIDEO_REGISTER_MSG + key);
    }

    /**
     * Send the Video complete event and remove the tracker from the dict
     * @param placementId
     */
    public void unregisterVideoMoatEvent(int placementId){
        /** go on */
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("type", "AdVideoComplete");
        String key = MOAT_VIDEO_KEY + placementId;
        NativeVideoTracker tracker = videoDict.get(key);

        if (tracker != null) {
            Log.d("SuperAwesome", MOAT_VIDEO_UNREGISTER_MSG + key);
            tracker.dispatchEvent(params);
            videoDict.remove(key);
        }
    }
}
