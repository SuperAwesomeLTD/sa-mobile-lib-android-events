package tv.superawesome.lib.saevents.modules;

import android.content.Context;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.Executor;

import tv.superawesome.lib.saevents.mocks.executors.MockExecutor;
import tv.superawesome.lib.saevents.mocks.server.events.MockEventsServer;
import tv.superawesome.lib.sasession.defines.SAConfiguration;
import tv.superawesome.lib.sasession.defines.SARTBInstl;
import tv.superawesome.lib.sasession.defines.SARTBPlaybackMethod;
import tv.superawesome.lib.sasession.defines.SARTBPosition;
import tv.superawesome.lib.sasession.defines.SARTBSkip;
import tv.superawesome.lib.sasession.defines.SARTBStartDelay;
import tv.superawesome.lib.sasession.session.SASession;
import tv.superawesome.lib.sautils.SAUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by gabriel.coman on 09/05/2018.
 */
public class TestModule {

    protected Context context   = null;
    protected Executor executor = null;
    protected SASession session = null;
    private MockEventsServer server;

    @Before
    public void setUp () throws Throwable {
        executor = new MockExecutor();

        context = mock(Context.class);
        session = mock(SASession.class);

        when(session.getBaseUrl()).thenReturn("http://localhost:64000");
        when(session.getTestMode()).thenReturn(true);
        when(session.getConfiguration()).thenReturn(SAConfiguration.PRODUCTION);
        when(session.getVersion()).thenReturn("1.0.0");
        when(session.getCachebuster()).thenReturn(123456);
        when(session.getPackageName()).thenReturn("superawesome.tv.saadloaderdemo");
        when(session.getAppName()).thenReturn("SAAdLoaderDemo");
        when(session.getDauId()).thenReturn(654321);
        when(session.getConnectionType()).thenReturn(SAUtils.SAConnectionType.wifi);
        when(session.getLang()).thenReturn("en_GB");
        when(session.getDevice()).thenReturn("phone");
        when(session.getPos()).thenReturn(SARTBPosition.FULLSCREEN);
        when(session.getSkip()).thenReturn(SARTBSkip.NO_SKIP);
        when(session.getPlaybackMethod()).thenReturn(SARTBPlaybackMethod.WITH_SOUND_ON_SCREEN);
        when(session.getStartDelay()).thenReturn(SARTBStartDelay.PRE_ROLL);
        when(session.getInstl()).thenReturn(SARTBInstl.FULLSCREEN);
        when(session.getWidth()).thenReturn(320);
        when(session.getHeight()).thenReturn(240);

        server = new MockEventsServer();
        server.start();
    }

    @After
    public void tearDown () throws Throwable {
        server.shutdown();
        server = null;
        executor = null;
        session = null;
    }
}
