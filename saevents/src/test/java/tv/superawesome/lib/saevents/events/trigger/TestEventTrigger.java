package tv.superawesome.lib.saevents.events.trigger;

import org.junit.After;
import org.junit.Before;

import tv.superawesome.lib.saevents.events.TestEvent;
import tv.superawesome.lib.saevents.mocks.server.events.MockEventsServer;

/**
 * Created by gabriel.coman on 09/05/2018.
 */

public class TestEventTrigger extends TestEvent {

    private MockEventsServer server;

    @Override
    @Before
    public void setUp () throws Throwable {
        super.setUp();

        server = new MockEventsServer();
        server.start();
    }

    @After
    public void tearDown () throws Throwable {
        super.tearDown();
        server.shutdown();
    }
}
