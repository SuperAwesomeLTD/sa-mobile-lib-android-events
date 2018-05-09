package tv.superawesome.lib.saevents.events.setup;

import org.junit.Assert;
import org.junit.Test;

import tv.superawesome.lib.saevents.events.SAURLEvent;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
public class TestSAURLEventSetup extends TestEventSetup {

    @Test
    public void test_SAURLEvent_Init () {
        // given
        String url = "https://api.url/abc";

        // when
        SAURLEvent event = new SAURLEvent(null, url, super.executor, 1000);

        // then - endpoint
        Assert.assertNotNull(event.getEndpoint());
        Assert.assertEquals("", event.getEndpoint());

        // then - url
        Assert.assertNotNull(event.getUrl());
        Assert.assertEquals("https://api.url/abc", event.getUrl());
    }
}
