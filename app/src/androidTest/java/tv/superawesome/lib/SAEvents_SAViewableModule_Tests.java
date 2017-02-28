package tv.superawesome.lib;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import java.util.concurrent.CountDownLatch;

import tv.superawesome.lib.saevents.SAViewableModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;

public class SAEvents_SAViewableModule_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAViewableModule_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final WebView webView = (WebView) getActivity().findViewById(R.id.TestWebView);

        SAAd ad = SAEvents_Aux.getTestAd();
        final SAViewableModule module = new SAViewableModule(ad);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                module.checkViewableStatusForDisplay(webView, new SAViewableModule.Listener() {
                    @Override
                    public void saDidFindViewOnScreen(boolean success) {
                        assertTrue(success);
                        signal.countDown();
                    }
                });

            }
        });

        signal.await();
    }

    @LargeTest
    public void test2 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final RelativeLayout playerHolder = (RelativeLayout) getActivity().findViewById(R.id.TestVideoPlayerHolder);

        SAAd ad = SAEvents_Aux.getTestAd();
        final SAViewableModule module = new SAViewableModule(ad);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                module.checkViewableStatusForVideo(playerHolder, new SAViewableModule.Listener() {
                    @Override
                    public void saDidFindViewOnScreen(boolean success) {
                        assertTrue(success);
                        signal.countDown();
                    }
                });

            }
        });

        signal.await();
    }

    @LargeTest
    public void test3 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final WebView webView = null;

        SAAd ad = SAEvents_Aux.getTestAd();
        final SAViewableModule module = new SAViewableModule(ad);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                module.checkViewableStatusForView(webView, 2, new SAViewableModule.Listener() {
                    @Override
                    public void saDidFindViewOnScreen(boolean success) {
                        assertFalse(success);
                        signal.countDown();
                    }
                });

            }
        });

        signal.await();
    }

    @LargeTest
    public void test4 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final WebView webView = null;
        SAAd ad = null;
        final SAViewableModule module = new SAViewableModule(ad);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                module.checkViewableStatusForView(webView, 2, new SAViewableModule.Listener() {
                    @Override
                    public void saDidFindViewOnScreen(boolean success) {
                        assertFalse(success);
                        signal.countDown();
                    }
                });

            }
        });

        signal.await();
    }

    @LargeTest
    public void test5 () throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final WebView webView = null;
        SAAd ad = null;
        final SAViewableModule module = new SAViewableModule(ad);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                module.checkViewableStatusForView(webView, 2, null);
                assertFalse(false);
                signal.countDown();

            }
        });

        signal.await();
    }
}
