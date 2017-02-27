package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import tv.superawesome.lib.saevents.events.SAServerEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAEvents_SAServerEvent_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAServerEvent_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAServerEvent event = new SAServerEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
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
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAServerEvent event = new SAServerEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
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
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        SAServerEvent event = new SAServerEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
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
        SAAd ad = null;
        SASession session = null;

        SAServerEvent event = new SAServerEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);

    }
}
