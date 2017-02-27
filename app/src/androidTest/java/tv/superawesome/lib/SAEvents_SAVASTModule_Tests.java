package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.List;

import tv.superawesome.lib.saevents.SAVASTModule;
import tv.superawesome.lib.saevents.events.SAURLEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;

public class SAEvents_SAVASTModule_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAVASTModule_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }


    @SmallTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        SAURLEvent event = module.getVastClickThrough();

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/click_through");

        String eventUrl = module.getVASTClickThroughEvent();
        assertNotNull(eventUrl);
        assertEquals(eventUrl, "https://ads.staging.superawesome/v2/2432/click_through");

    }

    @SmallTest
    public void test2 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastError();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/error");

    }

    @SmallTest
    public void test3 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastImpression();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/impression");

    }

    @SmallTest
    public void test4 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastClickTracking();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/click_tracking");

    }

    @SmallTest
    public void test5 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastCreativeView();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/creativeView");

    }

    @SmallTest
    public void test6 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastStart();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/start");

    }

    @SmallTest
    public void test7 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastFirstQuartile();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/firstQuartile");

    }

    @SmallTest
    public void test8 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastMidpoint();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/midpoint");

    }

    @SmallTest
    public void test9 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastThirdQuartile();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/thirdQuartile");

    }

    @SmallTest
    public void test10 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);

        List<SAURLEvent> list = module.getVastComplete();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        SAURLEvent event = list.get(0);

        assertNotNull(event);
        assertEquals(event.getUrl(), "https://ads.staging.superawesome/v2/2432/complete");

    }

    @SmallTest
    public void test11 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();

        SAVASTModule module = new SAVASTModule(context, ad);
        assertNotNull(module.getVastClickThrough());

        assertNotNull(module.getVastClickTracking());
        assertEquals(module.getVastClickTracking().size(), 1);

        assertNotNull(module.getVastError());
        assertEquals(module.getVastError().size(), 1);

        assertNotNull(module.getVastImpression());
        assertEquals(module.getVastImpression().size(), 1);

        assertNotNull(module.getVastCreativeView());
        assertEquals(module.getVastCreativeView().size(), 1);

        assertNotNull(module.getVastStart());
        assertEquals(module.getVastStart().size(), 1);

        assertNotNull(module.getVastFirstQuartile());
        assertEquals(module.getVastFirstQuartile().size(), 1);

        assertNotNull(module.getVastMidpoint());
        assertEquals(module.getVastMidpoint().size(), 1);

        assertNotNull(module.getVastThirdQuartile());
        assertEquals(module.getVastThirdQuartile().size(), 1);

        assertNotNull(module.getVastComplete());
        assertEquals(module.getVastComplete().size(), 1);

    }

    @SmallTest
    public void test12 () {

        Activity context = null;
        SAAd ad = null;

        SAVASTModule module = new SAVASTModule(context, ad);
        assertNull(module.getVastClickThrough());

        assertNotNull(module.getVastClickTracking());
        assertEquals(module.getVastClickTracking().size(), 0);

        assertNotNull(module.getVastError());
        assertEquals(module.getVastError().size(), 0);

        assertNotNull(module.getVastImpression());
        assertEquals(module.getVastImpression().size(), 0);

        assertNotNull(module.getVastCreativeView());
        assertEquals(module.getVastCreativeView().size(), 0);

        assertNotNull(module.getVastStart());
        assertEquals(module.getVastStart().size(), 0);

        assertNotNull(module.getVastFirstQuartile());
        assertEquals(module.getVastFirstQuartile().size(), 0);

        assertNotNull(module.getVastMidpoint());
        assertEquals(module.getVastMidpoint().size(), 0);

        assertNotNull(module.getVastThirdQuartile());
        assertEquals(module.getVastThirdQuartile().size(), 0);

        assertNotNull(module.getVastComplete());
        assertEquals(module.getVastComplete().size(), 0);

    }
}
