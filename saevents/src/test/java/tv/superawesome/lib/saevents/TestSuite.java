package tv.superawesome.lib.saevents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tv.superawesome.lib.saevents.events.TestSAClickEventSetup;
import tv.superawesome.lib.saevents.events.TestSAServerEventSetup;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestSAServerEventSetup.class,
        TestSAClickEventSetup.class
})
public class TestSuite {
}
