package tv.superawesome.lib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.moat.analytics.mobile.sup.MoatAdEvent;
import com.moat.analytics.mobile.sup.MoatAdEventType;
import com.moat.analytics.mobile.sup.MoatAnalytics;
import com.moat.analytics.mobile.sup.MoatFactory;
import com.moat.analytics.mobile.sup.MoatOptions;
import com.moat.analytics.mobile.sup.ReactiveVideoTracker;
import com.moat.analytics.mobile.sup.ReactiveVideoTrackerPlugin;
import com.moat.analytics.mobile.sup.WebAdTracker;

import java.util.HashMap;

import tv.superawesome.lib.saevents.SAEvents;
import tv.superawesome.lib.saevents.SAMoatModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.samodelspace.saad.SACampaignType;
import tv.superawesome.lib.samodelspace.saad.SACreativeFormat;
import tv.superawesome.lib.samodelspace.vastad.SAVASTEvent;
import tv.superawesome.lib.sanetwork.file.SAFileDownloader;
import tv.superawesome.lib.sanetwork.file.SAFileDownloaderInterface;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.savideoplayer.SAVideoPlayer;
import tv.superawesome.lib.savideoplayer.SAVideoPlayerEvent;
import tv.superawesome.lib.savideoplayer.SAVideoPlayerEventInterface;


public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String path;
    private SAVideoPlayer player;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final android.app.FragmentManager manager = getFragmentManager();

        final SAAd ad = getTestAd();

        final SAEvents events = new SAEvents();
        events.setAd(this, new SASession(this), ad);
        events.disableMoatLimiting();

        player = new SAVideoPlayer();
        player.setEventListener(new SAVideoPlayerEventInterface() {
            @Override
            public void saVideoPlayerDidReceiveEvent(SAVideoPlayerEvent saVideoPlayerEvent, int time, int duration) {

                switch (saVideoPlayerEvent) {
                    case Video_Prepared: {

                        try {
                            player.play(path);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }


                        break;
                    }

                    case Video_Start: {
                        boolean create = events.startMoatTrackingForVideoPlayer(player.getVideoPlayer(), duration);
                        Log.d("MoatAnalytics", "Create tracking:  " + create);

                        boolean start = events.sendMoatStartEvent(time);
                        Log.d("MoatAnalytics", "Send start: " + start);
                        boolean playing = events.sendMoatPlayingEvent(time);
                        Log.d("MoatAnalytics", "Send playing: " + playing);
                        break;
                    }
                    case Video_2s: {
                        boolean isViewable = events.isChildInRect(player.getVideoHolder());
                        if (isViewable) {
                            events.triggerViewableImpressionEvent();
                        }

                        break;
                    }
                    case Video_1_4: {
                        boolean event = events.sendMoatFirstQuartileEvent(time);
                        Log.d("MoatAnalytics", "Send 1/4: " + event);
                        break;
                    }
                    case Video_1_2: {
                        boolean event = events.sendMoatMidpointEvent(time);
                        Log.d("MoatAnalytics", "Send 1/2: " + event);
                        break;
                    }
                    case Video_3_4: {
                        boolean event = events.sendMoatThirdQuartileEvent(time);
                        Log.d("MoatAnalytics", "Send 3/4: " + event);
                        break;
                    }
                    case Video_End: {
                        boolean event = events.stopMoatTrackingForVideoPlayer();
                        Log.d("MoatAnalytics", "Stop tracking: " + event);
                        break;
                    }
                    case Video_15s: {
                        break;
                    }
                    case Video_Error: {
                        break;
                    }
                }
            }
        });

        SAFileDownloader.getInstance().downloadFileFrom(this, "https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/UAICy6n2MiSfyxmPoPjV4sqWPVXTRjVi.mp4", new SAFileDownloaderInterface() {
            @Override
            public void saDidDownloadFile(boolean b, String s) {
                path = s;
                manager.beginTransaction().add(R.id.MyCustomVideo, player, "PlayerTag").commit();
            }
        });

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.WebViewLayout);
        layout.addView(webView);

        String display = events.startMoatTrackingForDisplay(webView);
        Log.d("MoatAnalytics", "Moat tag is " + display);
        String html = "<html><body><img src='https://s3-eu-west-1.amazonaws.com/sb-ads-uploads/images/YkKgkIQYOiwV7WmbHK7jArBjHOrU3Bcn.jpg' width='100%' height='100%'>_MOAT_</body></html>"
                        .replace("_MOAT_", display);
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    public static SAAd getTestAd () {
        SAAd ad = new SAAd();
        ad.error = 0;
        ad.advertiserId = 1;
        ad.publisherId = 1;
        ad.appId = 1568;
        ad.lineItemId = 1137;
        ad.campaignId = 1269;
        ad.placementId = 628;
        ad.configuration = 1;
        ad.campaignType = SACampaignType.CPM;
        ad.moat = 0.2;
        ad.isPadlockVisible = true;
        ad.creative.id = 5879;
        ad.creative.format = SACreativeFormat.video;
        SAVASTEvent e1 = new SAVASTEvent();
        e1.event = "vast_creativeView";
        e1.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=creativeView&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=431411&device=web&country=GB";
        SAVASTEvent e2 = new SAVASTEvent();
        e2.event = "vast_start";
        e2.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=start&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=88060&device=web&country=GB";
        SAVASTEvent e3 = new SAVASTEvent();
        e3.event = "vast_firstQuartile";
        e3.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=firstQuartile&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=8852120&device=web&country=GB";
        SAVASTEvent e4 = new SAVASTEvent();
        e4.event = "vast_midpoint";
        e4.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=midpoint&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=9188166&device=web&country=GB";
        SAVASTEvent e5 = new SAVASTEvent();
        e5.event = "vast_thirdQuartile";
        e5.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=thirdQuartile&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=3030429&device=web&country=GB";
        SAVASTEvent e6 = new SAVASTEvent();
        e6.event = "vast_complete";
        e6.URL = "https://ads.staging.superawesome.tv/v2/video/tracking?event=complete&placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=3260747&device=web&country=GB";
        SAVASTEvent e7 = new SAVASTEvent();
        e7.event = "vast_error";
        e7.URL = "https://ads.staging.superawesome.tv/v2/video/error?placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=1176843&device=web&country=GB&code=[ERRORCODE]";
        SAVASTEvent e8 = new SAVASTEvent();
        e8.event = "vast_impression";
        e8.URL = "https://ads.staging.superawesome.tv/v2/video/impression?placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=1956493&device=web&country=GB";
        SAVASTEvent e9 = new SAVASTEvent();
        e9.event = "vast_click_tracking";
        e9.URL = "https://ads.staging.superawesome/v2/2432/click_tracking";
        SAVASTEvent e10 = new SAVASTEvent();
        e10.event = "vast_click_through";
        e10.URL = "https://ads.staging.superawesome.tv/v2/video/click?placement=628&creative=5879&line_item=1137&sdkVersion=unknown&rnd=8439823&device=web&country=GB";
        ad.creative.details.media.vastAd.events.add(e1);
        ad.creative.details.media.vastAd.events.add(e2);
        ad.creative.details.media.vastAd.events.add(e3);
        ad.creative.details.media.vastAd.events.add(e4);
        ad.creative.details.media.vastAd.events.add(e5);
        ad.creative.details.media.vastAd.events.add(e6);
        ad.creative.details.media.vastAd.events.add(e7);
        ad.creative.details.media.vastAd.events.add(e8);
        ad.creative.details.media.vastAd.events.add(e9);
        ad.creative.details.media.vastAd.events.add(e10);
        return ad;
    }
}
