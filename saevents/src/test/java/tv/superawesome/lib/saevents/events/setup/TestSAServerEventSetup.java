package tv.superawesome.lib.saevents.events.setup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tv.superawesome.lib.saevents.events.SAServerEvent;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class TestSAServerEventSetup extends TestEventSetup {

    private SAServerEvent event = null;

    @Override
    @Before
    public void setUp () throws Throwable {
        super.setUp();

        // create the event
        event = new SAServerEvent(null, ad, super.session, super.executor, 1000);
    }

    @Test
    public void test_ServerEvent_GetUrl () {
        Assert.assertNotNull(event);

        Assert.assertNotNull(event.getUrl());
        Assert.assertEquals("http://localhost:64000", event.getUrl());
        Assert.assertEquals(session.getBaseUrl(), event.getUrl());
    }

    @Test
    public void test_ServerEvent_GetHeader () throws Throwable {
        Assert.assertNotNull(event.getHeader());

        Assert.assertEquals("application/json", event.getHeader().getString("Content-Type"));
    }
}
