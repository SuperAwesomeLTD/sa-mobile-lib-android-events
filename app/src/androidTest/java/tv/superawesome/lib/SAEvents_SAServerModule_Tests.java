package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import tv.superawesome.lib.saevents.SAServerModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;

public class SAEvents_SAServerModule_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAServerModule_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAServerModule module = new SAServerModule(context, ad, session);

        assertNotNull(module.getClickEvent());
        assertNotNull(module.getImpressionEvent());
        assertNotNull(module.getViewableImpressionEvent());
        assertNotNull(module.getSapgCloseEvent());
        assertNotNull(module.getSapgOpenEvent());
        assertNotNull(module.getSapgFailEvent());
        assertNotNull(module.getSapgSuccessEvent());

    }

    @LargeTest
    public void test2 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAServerModule module = new SAServerModule(context, ad, session);

        assertNotNull(module.getClickEvent());
        assertNotNull(module.getImpressionEvent());
        assertNotNull(module.getViewableImpressionEvent());
        assertNotNull(module.getSapgCloseEvent());
        assertNotNull(module.getSapgOpenEvent());
        assertNotNull(module.getSapgFailEvent());
        assertNotNull(module.getSapgSuccessEvent());

    }

    @LargeTest
    public void test3 () {

        Activity context = null;
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = null;

        SAServerModule module = new SAServerModule(context, ad, session);

        assertNotNull(module.getClickEvent());
        assertNotNull(module.getImpressionEvent());
        assertNotNull(module.getViewableImpressionEvent());
        assertNotNull(module.getSapgCloseEvent());
        assertNotNull(module.getSapgOpenEvent());
        assertNotNull(module.getSapgFailEvent());
        assertNotNull(module.getSapgSuccessEvent());

    }

    @LargeTest
    public void test4 () {

        Activity context = null;
        SAAd ad = null;
        SASession session = null;

        SAServerModule module = new SAServerModule(context, ad, session);

        assertNotNull(module.getClickEvent());
        assertNotNull(module.getImpressionEvent());
        assertNotNull(module.getViewableImpressionEvent());
        assertNotNull(module.getSapgCloseEvent());
        assertNotNull(module.getSapgOpenEvent());
        assertNotNull(module.getSapgFailEvent());
        assertNotNull(module.getSapgSuccessEvent());

    }
}
