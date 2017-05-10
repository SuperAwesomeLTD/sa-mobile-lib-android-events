package tv.superawesome.lib.samoatevents;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.webkit.WebView;
import android.widget.VideoView;

import com.moat.analytics.mobile.sup.MoatAdEvent;
import com.moat.analytics.mobile.sup.MoatAdEventType;
import com.moat.analytics.mobile.sup.MoatAnalytics;
import com.moat.analytics.mobile.sup.MoatFactory;
import com.moat.analytics.mobile.sup.MoatOptions;
import com.moat.analytics.mobile.sup.NativeVideoTracker;
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
    private NativeVideoTracker videoTracker;

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
        return "<script src=\""+MOAT_SERVER+"/"+MOAT_DISPLAY_PARTNER_CODE+"/"+MOAT_URL+"?"+moatQuery+"\" type=\"text/javascript\"/>";
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

    public boolean startMoatTrackingForVideoPlayer(VideoView videoView, MediaPlayer mediaPlayer, HashMap<String, String> adDetails) {

        videoTracker = factory.createNativeVideoTracker(MOAT_VIDEO_PARTNER_CODE);

        if (videoTracker == null) return false;

        HashMap<String, String> adIds = new HashMap<>();
        adIds.put("level1", "" + adDetails.get("advertiserId"));
        adIds.put("level2", "" + adDetails.get("campaignId"));
        adIds.put("level3", "" + adDetails.get("lineItemId"));
        adIds.put("level4", "" + adDetails.get("creativeId"));
        adIds.put("slicer1", "" + adDetails.get("app"));
        adIds.put("slicer2", "" + adDetails.get("placementId"));
        adIds.put("slicer3", "" + adDetails.get("publisherId"));

        return videoTracker.trackVideoAd(adIds, mediaPlayer, videoView);
    }

    public boolean stopMoatTrackingForVideoPlayer() {
        MoatAdEvent event = new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE);
        if (videoTracker != null) {
            videoTracker.dispatchEvent(event);
            videoTracker.stopTracking();
            return true;
        } else {
            return false;
        }
    }
}
