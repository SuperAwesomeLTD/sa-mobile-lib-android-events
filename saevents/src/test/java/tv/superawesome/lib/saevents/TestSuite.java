package tv.superawesome.lib.saevents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tv.superawesome.lib.saevents.events.setup.TestSAClickEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestSAImpressionEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestSAServerEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestSAURLEventSetup;
import tv.superawesome.lib.saevents.events.setup.TestViewableImpressionEventSetup;
import tv.superawesome.lib.saevents.events.trigger.TestSAClickEventTrigger;
import tv.superawesome.lib.saevents.events.trigger.TestSAImpressionEventTrigger;
import tv.superawesome.lib.saevents.events.trigger.TestViewableImpressionEventTrigger;
import tv.superawesome.lib.saevents.modules.TestMoatModule;
import tv.superawesome.lib.saevents.modules.TestSAVASTModule;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // setup
        TestSAClickEventSetup.class,
        TestSAImpressionEventSetup.class,
        TestSAServerEventSetup.class,
        TestSAURLEventSetup.class,
        TestViewableImpressionEventSetup.class,

        // triggers
        TestSAClickEventTrigger.class,
        TestSAImpressionEventTrigger.class,
        TestViewableImpressionEventTrigger.class,
        TestSAURLEventSetup.class,

        // modules
        TestMoatModule.class,
        TestSAVASTModule.class,
        TestMoatModule.class
})
public class TestSuite {
}
