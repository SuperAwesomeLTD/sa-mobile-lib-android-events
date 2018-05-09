package tv.superawesome.lib.saevents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tv.superawesome.lib.saevents.events.setup.TestSAClickEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestSAImpressionEvent;
import tv.superawesome.lib.saevents.events.setup.TestSAServerEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestSAURLEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestViewableImpressionEventSetup;
import tv.superawesome.lib.saevents.events.trigger.TestSAClickEventTrigger;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // setup
        TestSAClickEventSetup.class,
        TestSAImpressionEvent.class,
        TestSAServerEventSetup.class,
        TestSAURLEventSetup.class,
        TestViewableImpressionEventSetup.class,

        // triggers
        TestSAClickEventTrigger.class
})
public class TestSuite {
}
