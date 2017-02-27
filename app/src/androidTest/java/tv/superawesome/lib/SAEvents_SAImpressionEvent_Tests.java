package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import tv.superawesome.lib.saevents.events.SAImpressionEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAEvents_SAImpressionEvent_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAImpressionEvent_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAImpressionEvent event = new SAImpressionEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/impression");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);
        assertTrue(query.has("placement"));
        assertEquals(query.opt("placement"), ad.placementId);
        assertTrue(query.has("bundle"));
        assertEquals(query.opt("bundle"), session.getPackageName());
        assertTrue(query.has("creative"));
        assertEquals(query.opt("creative"), ad.creative.id);
        assertTrue(query.has("line_item"));
        assertEquals(query.opt("line_item"), ad.lineItemId);
        assertTrue(query.has("ct"));
        assertEquals(query.opt("ct"), session.getConnectionType());
        assertTrue(query.has("sdkVersion"));
        assertEquals(query.opt("sdkVersion"), session.getVersion());
        assertTrue(query.has("no_image"));
        assertEquals(query.opt("no_image"), true);
        assertTrue(query.has("rnd"));

    }

    @LargeTest
    public void test2 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAImpressionEvent event = new SAImpressionEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/impression");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);
        assertTrue(query.has("placement"));
        assertEquals(query.opt("placement"), ad.placementId);
        assertTrue(query.has("bundle"));
        assertEquals(query.opt("bundle"), session.getPackageName());
        assertTrue(query.has("creative"));
        assertEquals(query.opt("creative"), ad.creative.id);
        assertTrue(query.has("line_item"));
        assertEquals(query.opt("line_item"), ad.lineItemId);
        assertTrue(query.has("ct"));
        assertEquals(query.opt("ct"), session.getConnectionType());
        assertTrue(query.has("sdkVersion"));
        assertEquals(query.opt("sdkVersion"), session.getVersion());
        assertTrue(query.has("no_image"));
        assertEquals(query.opt("no_image"), true);
        assertTrue(query.has("rnd"));

    }

    @LargeTest
    public void test3 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        SAImpressionEvent event = new SAImpressionEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "/impression");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);
        assertFalse(query.has("placement"));
        assertFalse(query.has("bundle"));
        assertFalse(query.has("creative"));
        assertFalse(query.has("line_item"));
        assertFalse(query.has("ct"));
        assertFalse(query.has("sdkVersion"));
        assertFalse(query.has("no_image"));
        assertFalse(query.has("rnd"));

    }

    @LargeTest
    public void test4 () {

        Activity context = null;
        SAAd ad = null;
        SASession session = null;

        SAImpressionEvent event = new SAImpressionEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "/impression");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));
        assertNotNull(query);
        assertFalse(query.has("placement"));
        assertFalse(query.has("bundle"));
        assertFalse(query.has("creative"));
        assertFalse(query.has("line_item"));
        assertFalse(query.has("ct"));
        assertFalse(query.has("sdkVersion"));
        assertFalse(query.has("no_image"));
        assertFalse(query.has("rnd"));

    }
}
