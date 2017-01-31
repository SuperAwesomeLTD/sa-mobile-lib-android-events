package tv.superawesome.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by gabriel.coman on 21/10/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SAEvents_Async_Tests.class,
        SAEvents_Moat_Tests.class
})
public class TestSuite {
}

