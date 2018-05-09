package tv.superawesome.lib.saevents.events.trigger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tv.superawesome.lib.saevents.events.SAClickEvent;
import tv.superawesome.lib.saevents.events.SAServerEvent;
import tv.superawesome.lib.saevents.mocks.models.MockDisplayAd;
import tv.superawesome.lib.samodelspace.saad.SAAd;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class TestSAClickEventTrigger extends TestEventTrigger {

    @Test
    public void test_SAClickEvent_triggerEvent_WithSuccess () {

        // given
        SAClickEvent event = new SAClickEvent(null, super.ad, super.session, super.executor, 1000);

        // when
        event.triggerEvent(new SAServerEvent.Listener() {
            @Override
            public void didTriggerEvent(boolean success) {

                // then
                Assert.assertTrue(success);
            }
        });
    }

    @Test
    public void test_SAClickEvent_triggerEvent_WithFailure () {

        SAAd ad = new MockDisplayAd(1001, null);

        // given
        SAClickEvent event = new SAClickEvent(null, ad, super.session, super.executor, 1000);

        // when
        event.triggerEvent(new SAServerEvent.Listener() {
            @Override
            public void didTriggerEvent(boolean success) {

                // then
                Assert.assertFalse(success);
            }
        });
    }
}
