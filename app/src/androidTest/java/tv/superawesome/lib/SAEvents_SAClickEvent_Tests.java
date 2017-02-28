package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

import tv.superawesome.lib.saevents.events.SAClickEvent;
import tv.superawesome.lib.saevents.events.SAServerEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAEvents_SAClickEvent_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAClickEvent_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAClickEvent event = new SAClickEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/video/click");
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
        assertTrue(query.has("rnd"));

    }

    @LargeTest
    public void test2 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAClickEvent event = new SAClickEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, session.getBaseUrl());
        assertEquals(endpoint, "/video/click");
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
        assertTrue(query.has("rnd"));

    }

    @LargeTest
    public void test3 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        SAClickEvent event = new SAClickEvent(context, ad, session);

        String url = event.getUrl();
        String endpoint = event.getEndpoint();
        JSONObject header = event.getHeader();
        JSONObject query = event.getQuery();

        assertEquals(url, null);
        assertEquals(endpoint, "/video/click");
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
        assertFalse(query.has("rnd"));

    }

    @LargeTest
    public void test4 () {

        Activity context = null;
        SAAd ad = null;
        SASession session = null;

        SAClickEvent event = new SAClickEvent(context, ad, session);

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
        assertFalse(query.has("placement"));
        assertFalse(query.has("bundle"));
        assertFalse(query.has("creative"));
        assertFalse(query.has("line_item"));
        assertFalse(query.has("ct"));
        assertFalse(query.has("sdkVersion"));
        assertFalse(query.has("rnd"));

    }

    @LargeTest
    public void test5 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        final SAClickEvent event = new SAClickEvent(context, ad, session);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                event.triggerEvent(new SAServerEvent.Listener() {
                    @Override
                    public void didTriggerEvent(boolean success) {

                        assertTrue(success);
                        signal.countDown();

                    }
                });

            }
        });

        signal.await();

    }

    @LargeTest
    public void test6 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        final SAClickEvent event = new SAClickEvent(context, ad, session);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                event.triggerEvent(new SAServerEvent.Listener() {
                    @Override
                    public void didTriggerEvent(boolean success) {

                        assertFalse(success);
                        signal.countDown();

                    }
                });

            }
        });

        signal.await();

    }

    @LargeTest
    public void test7 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        final SAClickEvent event = new SAClickEvent(context, ad, session);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                event.triggerEvent(new SAServerEvent.Listener() {
                    @Override
                    public void didTriggerEvent(boolean success) {

                        assertFalse(success);
                        signal.countDown();

                    }
                });

            }
        });

        signal.await();

    }
}
