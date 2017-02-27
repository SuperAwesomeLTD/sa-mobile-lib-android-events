package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import tv.superawesome.lib.saevents.events.SAPGSuccessEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAEvents_SAPGSuccessEvent_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAPGSuccessEvent_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAPGSuccessEvent event = new SAPGSuccessEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/event");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));

        assertTrue(query.has("bundle"));
        assertEquals(query.opt("bundle"), session.getPackageName());
        assertTrue(query.has("ct"));
        assertEquals(query.opt("ct"), session.getConnectionType());
        assertTrue(query.has("sdkVersion"));
        assertEquals(query.opt("sdkVersion"), session.getVersion());
        assertTrue(query.has("rnd"));
        assertTrue(query.has("data"));
        assertTrue(query.opt("data").toString().contains("placement%22%3A100"));
        assertTrue(query.opt("data").toString().contains("line_item%22%3A8921"));
        assertTrue(query.opt("data").toString().contains("creative%22%3A2432"));
        assertTrue(query.opt("data").toString().contains("type%22%3A%22parentalGateSuccess"));

    }

    @LargeTest
    public void test2 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAPGSuccessEvent event = new SAPGSuccessEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/event");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));

        assertTrue(query.has("bundle"));
        assertEquals(query.opt("bundle"), session.getPackageName());
        assertTrue(query.has("ct"));
        assertEquals(query.opt("ct"), session.getConnectionType());
        assertTrue(query.has("sdkVersion"));
        assertEquals(query.opt("sdkVersion"), session.getVersion());
        assertTrue(query.has("rnd"));
        assertTrue(query.has("data"));
        assertTrue(query.opt("data").toString().contains("placement%22%3A100"));
        assertTrue(query.opt("data").toString().contains("line_item%22%3A8921"));
        assertTrue(query.opt("data").toString().contains("creative%22%3A2432"));
        assertTrue(query.opt("data").toString().contains("type%22%3A%22parentalGateSuccess"));

    }

    @LargeTest
    public void test3 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        SAPGSuccessEvent event = new SAPGSuccessEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "/event");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));

        assertFalse(query.has("bundle"));
        assertFalse(query.has("ct"));
        assertFalse(query.has("sdkVersion"));
        assertFalse(query.has("rnd"));
        assertFalse(query.has("data"));

    }

    @LargeTest
    public void test4 () {

        Activity context = null;
        SAAd ad = null;
        SASession session = null;

        SAPGSuccessEvent event = new SAPGSuccessEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "/event");
        assertNotNull(header);
        assertTrue(header.has("Content-Type"));
        assertEquals(header.opt("Content-Type"), "application/json");
        assertTrue(header.has("User-Agent"));
        assertEquals(header.opt("User-Agent"), SAUtils.getUserAgent(context));

        assertFalse(query.has("bundle"));
        assertFalse(query.has("ct"));
        assertFalse(query.has("sdkVersion"));
        assertFalse(query.has("rnd"));
        assertFalse(query.has("data"));

    }
}
