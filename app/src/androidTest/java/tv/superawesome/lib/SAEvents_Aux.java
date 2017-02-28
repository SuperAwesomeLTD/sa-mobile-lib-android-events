package tv.superawesome.lib;

import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.samodelspace.saad.SACampaignType;
import tv.superawesome.lib.samodelspace.saad.SACreativeFormat;
import tv.superawesome.lib.samodelspace.vastad.SAVASTEvent;

public class SAEvents_Aux {

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
