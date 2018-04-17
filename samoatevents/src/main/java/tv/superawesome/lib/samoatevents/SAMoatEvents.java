package tv.superawesome.lib.samoatevents;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.moat.analytics.mobile.sup.MoatAdEvent;
import com.moat.analytics.mobile.sup.MoatAdEventType;
import com.moat.analytics.mobile.sup.MoatAnalytics;
import com.moat.analytics.mobile.sup.MoatFactory;
import com.moat.analytics.mobile.sup.MoatOptions;
import com.moat.analytics.mobile.sup.NativeVideoTracker;
import com.moat.analytics.mobile.sup.ReactiveVideoTracker;
import com.moat.analytics.mobile.sup.ReactiveVideoTrackerPlugin;
import com.moat.analytics.mobile.sup.WebAdTracker;

import java.util.HashMap;

public class SAMoatEvents {

    // Moat tracking hardcoded constants
    private static final String MOAT_SERVER                 = "https://z.moatads.com";
    private static final String MOAT_URL                    = "moatad.js";
    private static final String MOAT_DISPLAY_PARTNER_CODE   = "superawesomeinappdisplay731223424656";
    private static final String MOAT_VIDEO_PARTNER_CODE     = "superawesomeinappvideo467548716573";

    private MoatFactory factory;
    private WebAdTracker webTracker;
    private ReactiveVideoTracker videoTracker;

    public SAMoatEvents(Context activity) {

        MoatOptions options = new MoatOptions();
        options.disableAdIdCollection = true;
        options.disableLocationServices = true;
        options.loggingEnabled = true;
        MoatAnalytics.getInstance().start(options, ((Activity)activity).getApplication());

        factory = MoatFactory.create();
    }

    public String startMoatTrackingForDisplay(WebView webView, HashMap<String, String> adDetails) {
        webTracker = factory.createWebAdTracker(webView);

        if (webTracker == null) return "";

        // form the proper moat data
        String moatQuery = "";
        moatQuery += "moatClientLevel1=" + adDetails.get("advertiserId");
        moatQuery += "&moatClientLevel2=" + adDetails.get("campaignId");
        moatQuery += "&moatClientLevel3=" + adDetails.get("lineItemId");
        moatQuery += "&moatClientLevel4=" + adDetails.get("creativeId");
        moatQuery += "&moatClientSlicer1=" + adDetails.get("app");
        moatQuery += "&moatClientSlicer2=" + adDetails.get("placementId");
        moatQuery += "&moatClientSlicer3=" + adDetails.get("publisherId");

        webTracker.startTracking();

        // and return the special moat javascript tag to be loaded in a web view
        return "<script src=\""+MOAT_SERVER+"/"+MOAT_DISPLAY_PARTNER_CODE+"/"+MOAT_URL+"?"+moatQuery+"\" type=\"text/javascript\"></script>";
    }

    public boolean stopMoatTrackingForDisplay() {
        if (webTracker != null) {
            webTracker.stopTracking();
            webTracker = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean startMoatTrackingForVideoPlayer(VideoView videoView, HashMap<String, String> adDetails, int duration) {

        videoTracker = factory.createCustomTracker(new ReactiveVideoTrackerPlugin(MOAT_VIDEO_PARTNER_CODE));

        Log.d("SuperAwesome", "Starting Moat video for duration " + duration);

        if (videoTracker == null) return false;

        HashMap<String, String> adIds = new HashMap<>();
        adIds.put("level1", "" + adDetails.get("advertiserId"));
        adIds.put("level2", "" + adDetails.get("campaignId"));
        adIds.put("level3", "" + adDetails.get("lineItemId"));
        adIds.put("level4", "" + adDetails.get("creativeId"));
        adIds.put("slicer1", "" + adDetails.get("app"));
        adIds.put("slicer2", "" + adDetails.get("placementId"));
        adIds.put("slicer3", "" + adDetails.get("publisherId"));

        return videoTracker.trackVideoAd(adIds, duration, videoView);
    }

    public boolean sendPlayingEvent (int position) {
        if (videoTracker == null) return false;
        videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_PLAYING, position));
        return true;
    }

    public boolean sendStartEvent (int position) {
        if (videoTracker == null) return false;
        videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_START, position));
        return true;
    }

    public boolean sendFirstQuartileEvent (int position) {
        if (videoTracker == null) return false;
        videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_FIRST_QUARTILE, position));
        return true;
    }

    public boolean sendMidpointEvent (int position) {
        if (videoTracker == null) return false;
        videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_MID_POINT, position));
        return true;
    }

    public boolean sendThirdQuartileEvent (int position) {
        if (videoTracker == null) return false;
        videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_THIRD_QUARTILE, position));
        return true;
    }

    public boolean stopMoatTrackingForVideoPlayer() {
        if (videoTracker != null) {
            videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            videoTracker.stopTracking();
            return true;
        } else {
            return false;
        }
    }
}
