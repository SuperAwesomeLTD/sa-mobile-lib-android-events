package tv.superawesome.lib;

import android.app.Application;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.webkit.WebView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import tv.superawesome.lib.saevents.SAEvents;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.savideoplayer.SAVideoPlayer;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SAEvents_Moat_Tests extends ApplicationTestCase<Application> {

    public SAEvents_Moat_Tests() {
        super(Application.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void setupTest() {
    }

    @Test
    public void testMoatForViews () {

        String json = "{\n" +
                "    \"is_house\": false,\n" +
                "    \"show_padlock\": true,\n" +
                "    \"vastType\": 0,\n" +
                "    \"creative\": {\n" +
                "        \"id\": 4907,\n" +
                "        \"creativeFormat\": 1,\n" +
                "        \"events\": [\n" +
                "                   {\n" +
                "                   \"event\": \"sa_tracking\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?creative=4907&line_item=932&placement=481&ct=0&rnd=1267293&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"viewable_impr\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1469903&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22viewable_impression%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_success\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1440263&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateSuccess%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_open\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1438899&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateOpen%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_close\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1370068&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateClose%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_fail\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1320452&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateFail%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"sa_impr\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/impression?creative=4907&line_item=932&placement=481&rnd=1151004&sdkVersion=ios_5.2.3&no_image=1\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"click_through\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8205269&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"creativeView\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=creativeView&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8903022&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"start\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=start&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8592053&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"firstQuartile\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=firstQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1482271&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"midpoint\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=midpoint&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=6617407&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"thirdQuartile\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=thirdQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9813644&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"complete\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=complete&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=366666&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"mute\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=mute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9234219&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"unmute\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=unmute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5763996&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pause\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=pause&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=872445&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"rewind\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=rewind&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=3073143&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"resume\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=resume&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1385198&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"skip\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"error\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/error?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5391079&dauid=8798453893251470766&device=phone&code=[ERRORCODE]\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging\"\n" +
                "                   }\n" +
                "                   ],\n" +
                "        \"bundleId\": null,\n" +
                "        \"approved\": false,\n" +
                "        \"installUrl\": null,\n" +
                "        \"click_url\": \"https://superawesome.tv\",\n" +
                "        \"details\": {\n" +
                "            \"vast\": \"https://ads.staging.superawesome.tv/v2/video/vast/481/932/4907/?sdkVersion=ios_5.2.3&rnd=621706701&dauid=8798453893251470766&device=phone\",\n" +
                "            \"width\": 600,\n" +
                "            \"placement_format\": \"video\",\n" +
                "            \"url\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "            \"bitrate\": 0,\n" +
                "            \"value\": 0,\n" +
                "            \"image\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "            \"tag\": null,\n" +
                "            \"cdnUrl\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/\",\n" +
                "            \"height\": 480,\n" +
                "            \"zipFile\": null,\n" +
                "            \"duration\": 32,\n" +
                "            \"media\": {\n" +
                "                \"isOnDisk\": true,\n" +
                "                \"playableDiskUrl\": \"samov_19410.mp4\",\n" +
                "                \"playableMediaUrl\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "                \"html\": null,\n" +
                "                \"type\": \"video/mp4\"\n" +
                "            },\n" +
                "            \"name\": null,\n" +
                "            \"video\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\"\n" +
                "        },\n" +
                "        \"customPayload\": null,\n" +
                "        \"live\": false,\n" +
                "        \"format\": \"video\",\n" +
                "        \"impression_url\": null,\n" +
                "        \"name\": null,\n" +
                "        \"cpm\": 0\n" +
                "    },\n" +
                "    \"advertiserId\": 1,\n" +
                "    \"publisherId\": 1,\n" +
                "    \"is_fallback\": false,\n" +
                "    \"error\": 0,\n" +
                "    \"vastRedirect\": null,\n" +
                "    \"app\": 1484,\n" +
                "    \"isVAST\": false,\n" +
                "    \"placementId\": 481,\n" +
                "    \"safe_ad_approved\": true,\n" +
                "    \"campaign_id\": 0,\n" +
                "    \"test\": false,\n" +
                "    \"is_fill\": false,\n" +
                "    \"line_item_id\": 932\n" +
                "}";

        // assert activity is OK
        assertNotNull(mActivityRule.getActivity());

        // get the test web view and assert it's OK
        WebView testWebView = (WebView) mActivityRule.getActivity().findViewById(R.id.MyWebView);
        assertNotNull(testWebView);

        // create ad object
        SAAd ad = new SAAd(json);

        SAEvents events = new SAEvents(mActivityRule.getActivity());
        events.setAd(ad);
        events.disableMoatLimiting();

        assertNotNull(events);

        // get the moat string
        String moatString = events.registerDisplayMoatEvent(mActivityRule.getActivity(), testWebView);

        assertNotNull(moatString);
        assertTrue(moatString.contains("https://z.moatads.com/"));
        assertTrue(moatString.contains("superawesomeinappdisplay731223424656"));
        assertTrue(moatString.contains("moatClientLevel1=1"));
        assertTrue(moatString.contains("moatClientLevel2=0"));
        assertTrue(moatString.contains("moatClientLevel3=932"));
        assertTrue(moatString.contains("moatClientLevel4=4907"));
        assertTrue(moatString.contains("moatClientSlicer1=1484"));
        assertTrue(moatString.contains("moatClientSlicer2=481"));
        assertTrue(moatString.contains("moatClientSlicer3=1"));

        // now unregister
        boolean removeSuccess = events.unregisterDisplayMoatEvent();
        assertTrue(removeSuccess);

    }

    @Test
    public void testMoatForVideo () {

        String json = "{\n" +
                "    \"is_house\": false,\n" +
                "    \"show_padlock\": true,\n" +
                "    \"vastType\": 0,\n" +
                "    \"creative\": {\n" +
                "        \"id\": 4907,\n" +
                "        \"creativeFormat\": 1,\n" +
                "        \"events\": [\n" +
                "                   {\n" +
                "                   \"event\": \"sa_tracking\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?creative=4907&line_item=932&placement=481&ct=0&rnd=1267293&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"viewable_impr\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1469903&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22viewable_impression%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_success\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1440263&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateSuccess%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_open\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1438899&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateOpen%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_close\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1370068&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateClose%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pg_fail\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1320452&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateFail%22%7D&sdkVersion=ios_5.2.3\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"sa_impr\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/impression?creative=4907&line_item=932&placement=481&rnd=1151004&sdkVersion=ios_5.2.3&no_image=1\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"click_through\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8205269&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"creativeView\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=creativeView&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8903022&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"start\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=start&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8592053&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"firstQuartile\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=firstQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1482271&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"midpoint\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=midpoint&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=6617407&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"thirdQuartile\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=thirdQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9813644&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"complete\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=complete&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=366666&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"mute\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=mute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9234219&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"unmute\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=unmute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5763996&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"pause\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=pause&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=872445&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"rewind\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=rewind&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=3073143&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"resume\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=resume&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1385198&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"skip\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"error\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/error?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5391079&dauid=8798453893251470766&device=phone&code=[ERRORCODE]\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                   \"event\": \"err_impression\",\n" +
                "                   \"URL\": \"https://ads.staging\"\n" +
                "                   }\n" +
                "                   ],\n" +
                "        \"bundleId\": null,\n" +
                "        \"approved\": false,\n" +
                "        \"installUrl\": null,\n" +
                "        \"click_url\": \"https://superawesome.tv\",\n" +
                "        \"details\": {\n" +
                "            \"vast\": \"https://ads.staging.superawesome.tv/v2/video/vast/481/932/4907/?sdkVersion=ios_5.2.3&rnd=621706701&dauid=8798453893251470766&device=phone\",\n" +
                "            \"width\": 600,\n" +
                "            \"placement_format\": \"video\",\n" +
                "            \"url\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "            \"bitrate\": 0,\n" +
                "            \"value\": 0,\n" +
                "            \"image\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "            \"tag\": null,\n" +
                "            \"cdnUrl\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/\",\n" +
                "            \"height\": 480,\n" +
                "            \"zipFile\": null,\n" +
                "            \"duration\": 32,\n" +
                "            \"media\": {\n" +
                "                \"isOnDisk\": true,\n" +
                "                \"playableDiskUrl\": \"samov_19410.mp4\",\n" +
                "                \"playableMediaUrl\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\",\n" +
                "                \"html\": null,\n" +
                "                \"type\": \"video/mp4\"\n" +
                "            },\n" +
                "            \"name\": null,\n" +
                "            \"video\": \"https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/l2UWsR6EWLZ8amjR8dTierr9hNS1mkOP.mp4\"\n" +
                "        },\n" +
                "        \"customPayload\": null,\n" +
                "        \"live\": false,\n" +
                "        \"format\": \"video\",\n" +
                "        \"impression_url\": null,\n" +
                "        \"name\": null,\n" +
                "        \"cpm\": 0\n" +
                "    },\n" +
                "    \"advertiserId\": 1,\n" +
                "    \"publisherId\": 1,\n" +
                "    \"is_fallback\": false,\n" +
                "    \"error\": 0,\n" +
                "    \"vastRedirect\": null,\n" +
                "    \"app\": 1484,\n" +
                "    \"isVAST\": false,\n" +
                "    \"placementId\": 481,\n" +
                "    \"safe_ad_approved\": true,\n" +
                "    \"campaign_id\": 0,\n" +
                "    \"test\": false,\n" +
                "    \"is_fill\": false,\n" +
                "    \"line_item_id\": 932\n" +
                "}";

        // create ad object
        SAAd ad = new SAAd(json);
        assertNotNull(ad);

        // assert the activity still exists
        assertNotNull(mActivityRule.getActivity());

        // get the video player
        SAVideoPlayer videoPlayer = (SAVideoPlayer) mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.sa_videoplayer_id);
        assertNotNull(videoPlayer);

        // create the new event
        SAEvents events = new SAEvents(mActivityRule.getActivity());
        events.disableMoatLimiting();
        events.setAd(ad);

        assertNotNull(events);

        // add moat tracking
        boolean enableMoatTracking = events.registerVideoMoatEvent(mActivityRule.getActivity(), videoPlayer.getVideoPlayer(), videoPlayer.getMediaPlayer());
        assertTrue (enableMoatTracking);

        // remove moat tracking
        boolean disableMoatTracking = events.unregisterVideoMoatEvent();
        assertTrue(disableMoatTracking);
    }

    @Test
    public void testMoatWithNoAd () {
        // check activity is still there
        assertNotNull(mActivityRule.getActivity());

        // create an events object, but specify no ad
        SAEvents events = new SAEvents(mActivityRule.getActivity());
        events.disableMoatLimiting();

        // assert events is still a valid object
        assertNotNull(events);

        // get & assert not null the web view
        WebView testWebView = (WebView) mActivityRule.getActivity().findViewById(R.id.MyWebView);
        assertNotNull(testWebView);

        // try to register a moat event for the webview
        String moatString = events.registerDisplayMoatEvent(mActivityRule.getActivity(), testWebView);

        // and assert the return string is a non-null empty string
        assertNotNull(moatString);
        assertTrue(moatString.equals(""));

        // also get the video player and assert that it exists
        SAVideoPlayer videoPlayer = (SAVideoPlayer) mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.sa_videoplayer_id);
        assertNotNull(videoPlayer);

        // make sure moat could not register an event bc the ad is empty
        boolean enableMoatTracking = events.registerVideoMoatEvent(mActivityRule.getActivity(), videoPlayer.getVideoPlayer(), videoPlayer.getMediaPlayer());
        assertFalse(enableMoatTracking);
    }
}
