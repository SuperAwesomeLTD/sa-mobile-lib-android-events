package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import tv.superawesome.lib.saevents.events.SAURLEvent;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAEvents_SAURLEvent_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAURLEvent_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        String eventUrl = "https://ads.staging.superawesome.tv/v2/test_event";
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAURLEvent event = new SAURLEvent(context, eventUrl);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, eventUrl);
        assertEquals(endpoint, "");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);
    }

    @LargeTest
    public void test2 () {

        Activity context = null;
        String eventUrl = "https://ads.staging.superawesome.tv/v2/test_event";
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAURLEvent event = new SAURLEvent(context, eventUrl);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, eventUrl);
        assertEquals(endpoint, "");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);

    }

    @LargeTest
    public void test3 () {

        Activity context = null;
        String eventUrl = "https://ads.staging.superawesome.tv/v2/test_event";

        SAURLEvent event = new SAURLEvent(context, eventUrl);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, eventUrl);
        assertEquals(endpoint, "");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);

    }

    @LargeTest
    public void test4 () {

        Activity context = null;
        String eventUrl = null;
        SASession session = null;

        SAURLEvent event = new SAURLEvent(context, eventUrl);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, eventUrl);
        assertEquals(endpoint, "");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);

    }
}
