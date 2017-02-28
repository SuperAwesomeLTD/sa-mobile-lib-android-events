package tv.superawesome.lib;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import tv.superawesome.lib.saevents.SAEvents;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;

public class SAEvents_SAEvents_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAEvents_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @SmallTest
    public void test1 () {

        Activity context = getActivity();
        SAAd ad = SAEvents_Aux.getTestAd();
        SASession session = new SASession(context);
        session.setConfigurationStaging();

        SAEvents events = new SAEvents();
        events.setAd(context, session, ad);

        assertNotNull(events.getServerModule());
        assertNotNull(events.getVastModule());
        assertNotNull(events.getMoatModule());
        assertNotNull(events.getViewableModule());

        events.unsetAd();

        assertNull(events.getServerModule());
        assertNull(events.getVastModule());
        assertNull(events.getMoatModule());
        assertNull(events.getViewableModule());
    }
}
