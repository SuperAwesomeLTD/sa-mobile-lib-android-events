package tv.superawesome.lib;

import android.os.Looper;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tv.superawesome.lib.saevents.SAEvents;
import tv.superawesome.lib.saevents.SAEventsInterface;
import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.samodelspace.SATracking;

public class SAEvents_Async_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final int TIMEOUT = 2500;

    private static final String json = "{\n" +
            "    \"is_house\": false,\n" +
            "    \"show_padlock\": true,\n" +
            "    \"vastType\": 0,\n" +
            "    \"creative\": {\n" +
            "        \"id\": 4907,\n" +
            "        \"creativeFormat\": 1,\n" +
            "        \"events\": [\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_click\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?creative=4907&line_item=932&placement=481&ct=0&rnd=1267293&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_viewable_impression\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1469903&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22viewable_impression%22%7D&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_pg_success\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1440263&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateSuccess%22%7D&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_pg_open\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1438899&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateOpen%22%7D&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_pg_close\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1370068&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateClose%22%7D&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_pg_fail\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/event?rnd=1320452&ct=0&data=%7B%22creative%22%3A4907%2C%22line_item%22%3A932%2C%22placement%22%3A481%2C%22type%22%3A%22parentalGateFail%22%7D&sdkVersion=ios_5.2.3\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"superawesome_impression\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/impression?creative=4907&line_item=932&placement=481&rnd=1151004&sdkVersion=ios_5.2.3&no_image=1\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_click_through\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/click?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8205269&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_creativeView\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=creativeView&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8903022&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_start\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=start&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8592053&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_firstQuartile\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=firstQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1482271&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_midpoint\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=midpoint&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=6617407&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_thirdQuartile\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=thirdQuartile&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9813644&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_complete\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=complete&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=366666&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_mute\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=mute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=9234219&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_unmute\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=unmute&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5763996&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_pause\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=pause&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=872445&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_rewind\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=rewind&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=3073143&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_resume\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=resume&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1385198&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_skip\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_error\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/error?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=5391079&dauid=8798453893251470766&device=phone&code=[ERRORCODE]\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_impression\",\n" +
            "                   \"URL\": \"https://ads.superawesome.tv/v2/video/impression?placement=28000&creative=-1&line_item=-1&sdkVersion=unknown&rnd=9305456&device=web\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_impression\",\n" +
            "                   \"URL\": \"https://ads.superawesome.tv/v2/video/impression?placement=28000&creative=-1&line_item=-1&sdkVersion=unknown&rnd=9305456&device=web\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_err_impression\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/impression?placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=8832683&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_err_impression\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
            "                   },\n" +
            "                   {\n" +
            "                   \"event\": \"vast_err_impression\",\n" +
            "                   \"URL\": \"https://ads.staging.superawesome.tv/v2/video/tracking?event=skip&placement=481&creative=4907&line_item=932&sdkVersion=ios_5.2.3&rnd=1570851&dauid=8798453893251470766&device=phone\"\n" +
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

    public SAEvents_Async_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @UiThreadTest
    @LargeTest
    public void test1 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        List<String> tracks = getFromArray(ad.creative.events, "superawesome_impression");
        String track = tracks.get(0);

        assertEquals(tracks.size(), 1);
        assertNotNull(track);

        events.sendEventToURL(track, new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertTrue(success);
                assertEquals(status, 200);
            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test2 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        List<String> tracks = getFromArray(ad.creative.events, "superawesome_pg_success");
        String track = tracks.get(0);

        assertEquals(tracks.size(), 1);
        assertNotNull(track);

        events.sendEventToURL(track, new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertTrue(success);
                // assertEquals(status, 200);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test3 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        List<String> tracks = getFromArray(ad.creative.events, "vast_firstQuartile");
        String track = tracks.get(0);

        assertEquals(tracks.size(), 1);
        assertNotNull(track);

        events.sendEventToURL(track, new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertTrue(success);
                assertEquals(status, 200);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test4 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        List<String> tracks = getFromArray(ad.creative.events, "NonExistentOne");
        String track = tracks.size() > 0 ? tracks.get(0) : null;

        assertEquals(tracks.size(), 0);
        assertNull(track);

        events.sendEventToURL(track, new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertFalse(success);
                assertEquals(status, 0);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test5 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        events.sendEventsFor("superawesome_impression", new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertTrue(success);
                assertEquals(status, 200);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test6 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        events.sendEventsFor("vast_err_impression", new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertTrue(success);
                assertEquals(status, 200);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test7 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        events.sendEventsFor("NonExistent", new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertFalse(success);
                assertEquals(status, 0);

            }
        });

        sleep(TIMEOUT);
    }

    @UiThreadTest
    @LargeTest
    public void test8 () {

        // create ad object
        JSONObject jsonObject = SAJsonParser.newObject(json);
        SAAd ad = new SAAd(jsonObject);

        // create events object
        SAEvents events = new SAEvents(getActivity());
        events.setAd(ad);

        assertNotNull(events);
        assertNotNull(ad);
        assertEquals(ad.creative.events.size(), 26);

        events.sendEventsFor(null, new SAEventsInterface() {
            @Override
            public void saDidGetEventResponse(boolean success, int status) {

                assertFalse(success);
                assertEquals(status, 0);

            }
        });

        sleep(TIMEOUT);
    }

    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            fail("Unexpected Timeout");
        }
    }

    public static List<String> getFromArray (List<SATracking> events, String key) {
        List<String> urls = new ArrayList<>();
        for (SATracking event : events) {
            if (event.event.equals(key)) {
                urls.add(event.URL);
            }
        }
        return urls;
    }
}
