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
    private MoatFactory         factory             = null;
    private WebAdTracker        webAdTracker        = null;
    private NativeVideoTracker  nativeVideoTracker  = null;

    /**
     * Public default contructor
     */
    public SAMoatEvents (Activity activity) {
        factory = MoatFactory.create(activity);
    }

    /**
     * Method that takes a view and some details and starts the Moat tracking process
     *
     * @param view          the WebView to register the moat event for
     * @param adDetails     ad details (placement id, campaign id, etc)
     * @return              a string containing the proper Moat javascript code to execute in the
     *                      web view, or an empty string if there was an error
     */
    public String registerDisplayMoatEvent(WebView view, HashMap<String, String> adDetails) {

        // create a web ad tracker for the view
        webAdTracker = factory.createWebAdTracker(view);

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

        // start tracking
        webAdTracker.track();

        // and return the special moat javascript tag to be loaded in a web view
        return "<script src=\""+MOAT_SERVER+"/"+MOAT_DISPLAY_PARTNER_CODE+"/"+MOAT_URL+"?"+moatQuery+"\" type=\"text/javascript\"></script>";
    }

    public boolean startDisplayTracking () {
//        webTracker.startTracking();
        return true;
    }

    /**
     * Method that destroys the web ad tracker and always returns true
     *
     * @return always true
     */
    public boolean unregisterDisplayMoatEvent () {
        webAdTracker = null;
        return true;
    }

    /**
     * Method that registers a new native video tracker and starts tracking the video ad
     *
     * @param video     the video view
     * @param mp        the media player
     * @param adDetails ad data to send
     * @return          true or false, depending if the tracker is OK
     */
    public boolean registerVideoMoatEvent(VideoView video, MediaPlayer mp, HashMap<String, String> adDetails){

        // create the native video tracker
        nativeVideoTracker = factory.createNativeVideoTracker(MOAT_VIDEO_PARTNER_CODE);

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

        // begin tracking
        return nativeVideoTracker.trackVideoAd(adIds, mp, video);
    }

    /**
     * Method that unregisters a video moat event
     *
     * @return always true
     */
    public boolean unregisterVideoMoatEvent(){

        // create a "complete" event
        MoatAdEvent completeEvent = new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE);

        // dispatch the event
        nativeVideoTracker.dispatchEvent(completeEvent);

        // always return true
        return true;
    }
}
