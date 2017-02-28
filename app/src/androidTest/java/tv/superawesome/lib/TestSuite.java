package tv.superawesome.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SAEvents_SAClickEvent_Tests.class,
        SAEvents_SAImpressionEvent_Tests.class,
        SAEvents_SAPGCloseEvent_Tests.class,
        SAEvents_SAPGFailEvent_Tests.class,
        SAEvents_SAPGOpenEvent_Tests.class,
        SAEvents_SAPGSuccessEvent_Tests.class,
        SAEvents_SAServerEvent_Tests.class,
        SAEvents_SAURLEvent_Tests.class,
        SAEvents_SAViewableImpressionEvent_Tests.class,
        SAEvents_SAServerModule_Tests.class,
        SAEvents_SAVASTModule_Tests.class,
        SAEvents_SAViewableModule_Tests.class,
        SAEvents_SAMoatModule_Tests.class,
        SAEvents_SAEvents_Tests.class
})
public class TestSuite {
}

