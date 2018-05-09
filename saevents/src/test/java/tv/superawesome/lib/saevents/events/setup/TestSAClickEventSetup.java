package tv.superawesome.lib.saevents.events.setup;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tv.superawesome.lib.saevents.events.SAClickEvent;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
public class TestSAClickEventSetup extends TestEventSetup {

    private SAClickEvent event = null;

    @Override
    @Before
    public void setUp () throws Throwable {
        super.setUp();
        event = new SAClickEvent(null, super.ad, super.session, super.executor, 1000);
    }

    @Test
    public void test_ClickEvent_GetEndpoint () {
        Assert.assertNotNull(event.getEndpoint());
        Assert.assertEquals("/click", event.getEndpoint());
    }

    @Test
    public void test_ClickEvent_GetQuery () throws Exception {
        Assert.assertNotNull(event.getQuery());

        JSONObject query = event.getQuery();

        Assert.assertEquals(2, query.get("ct"));
        Assert.assertEquals(2001, query.get("line_item"));
        Assert.assertEquals(123456, query.get("rnd"));
        Assert.assertEquals("1.0.0", query.get("sdkVersion"));
        Assert.assertEquals(1000, query.get("placement"));
        Assert.assertEquals("superawesome.tv.saadloaderdemo", query.get("bundle"));
        Assert.assertEquals(5001, query.get("creative"));
    }
}
