package tv.superawesome.lib.saevents.events.setup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tv.superawesome.lib.saevents.events.SAURLEvent;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class TestSAURLEventSetup extends TestEventSetup {

    private SAURLEvent event = null;

    @Override
    @Before
    public void setUp () throws Throwable {
        super.setUp();

        String url = "https://api.url/abc";
        event = new SAURLEvent(null, url, super.executor, 1000);
    }

    @Test
    public void test_URLEvent_GetEndpoint () {

        Assert.assertNotNull(event.getEndpoint());
        Assert.assertEquals("", event.getEndpoint());
    }

    @Test
    public void test_URLEvent_GetUrl () {
        Assert.assertNotNull(event.getUrl());
        Assert.assertEquals("https://api.url/abc", event.getUrl());
    }
}
