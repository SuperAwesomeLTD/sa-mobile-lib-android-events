package tv.superawesome.lib;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.webkit.WebView;

import tv.superawesome.lib.saevents.SAMoatModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sanetwork.file.SAFileDownloader;
import tv.superawesome.lib.sanetwork.file.SAFileDownloaderInterface;

public class SAEvents_SAMoatModule_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

//    private static final int TIMEOUT = 32500;
//
//    private String path;
    public SAEvents_SAMoatModule_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @SmallTest
    public void test0 () {
        boolean myVal = true;
        assertTrue(myVal);
    }
//
//    @UiThreadTest
//    @LargeTest
//    public void test0 () {
//
//        SAFileDownloader.getInstance().downloadFileFrom(getActivity(), "https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/UAICy6n2MiSfyxmPoPjV4sqWPVXTRjVi.mp4", new SAFileDownloaderInterface() {
//            @Override
//            public void saDidDownloadFile(boolean b, String s) {
//                path = s;
//            }
//        });
//
//        sleep(2500);
//
//    }
//
//    @LargeTest
//    public void test1 () {
//
////        SAAd ad = SAEvents_Aux.getTestAd();
////        SAMoatModule module = new SAMoatModule(getActivity(), ad);
////        module.disableMoatLimiting();
////
////        WebView webView = (WebView) getActivity().findViewById(R.id.TestWebView);
////
////        String moat = module.startMoatTrackingForDisplay(webView);
////        assertNotNull(moat);
////        assertTrue(moat.contains("z.moatads.com"));
////        assertTrue(moat.contains("superawesomeinappdisplay"));
////        assertTrue(moat.contains("731223424656"));
////        assertTrue(moat.contains("moatClientLevel1="+ad.advertiserId));
////        assertTrue(moat.contains("moatClientLevel2="+ad.campaignId));
////        assertTrue(moat.contains("moatClientLevel3="+ad.lineItemId));
////        assertTrue(moat.contains("moatClientLevel4="+ad.creative.id));
////        assertTrue(moat.contains("moatClientSlicer1="+ad.appId));
////        assertTrue(moat.contains("moatClientSlicer2="+ad.placementId));
////        assertTrue(moat.contains("moatClientSlicer3="+ad.publisherId));
////
////        boolean operation = module.stopMoatTrackingForDisplay();
////        assertTrue(operation);
//    }
//
//    @LargeTest
//    public void test2 () {
//
//        SAAd ad = SAEvents_Aux.getTestAd();
//        SAMoatModule module = new SAMoatModule(getActivity(), ad);
//        module.disableMoatLimiting();
//
//        WebView webView = null;
//
//        String moat = module.startMoatTrackingForDisplay(webView);
//        assertNotNull(moat);
//        assertTrue(moat.contains("z.moatads.com"));
//        assertTrue(moat.contains("superawesomeinappdisplay"));
//        assertTrue(moat.contains("731223424656"));
//        assertTrue(moat.contains("moatClientLevel1="+ad.advertiserId));
//        assertTrue(moat.contains("moatClientLevel2="+ad.campaignId));
//        assertTrue(moat.contains("moatClientLevel3="+ad.lineItemId));
//        assertTrue(moat.contains("moatClientLevel4="+ad.creative.id));
//        assertTrue(moat.contains("moatClientSlicer1="+ad.appId));
//        assertTrue(moat.contains("moatClientSlicer2="+ad.placementId));
//        assertTrue(moat.contains("moatClientSlicer3="+ad.publisherId));
//
//        boolean operation = module.stopMoatTrackingForDisplay();
//        assertTrue(operation);
//    }
//
//    @LargeTest
//    public void test3 () {
//
//        SAAd ad = null;
//        SAMoatModule module = new SAMoatModule(getActivity(), ad);
//        module.disableMoatLimiting();
//
//        WebView webView = null;
//
//        String moat = module.startMoatTrackingForDisplay(webView);
//        assertNotNull(moat);
//        assertTrue(moat.isEmpty());
//
//        boolean operation = module.stopMoatTrackingForDisplay();
//        assertFalse(operation);
//    }
//
//    @UiThreadTest
//    @LargeTest
//    public void test4 () throws Throwable {
//
//        Log.d("SuperAwesome", "TEST4 " + path);
//
////        SAAd ad = SAEvents_Aux.getTestAd();
////        final SAMoatModule module = new SAMoatModule(getActivity(), ad);
////        module.disableMoatLimiting();
////
////        final SAVideoPlayer videoPlayer = new SAVideoPlayer();
////        final FragmentManager manager = getActivity().getFragmentManager();
////        final String[] path = {};
////
////        videoPlayer.setEventListener(new SAVideoPlayerEventInterface() {
////            @Override
////            public void saVideoPlayerDidReceiveEvent(SAVideoPlayerEvent event) {
////                if (event == SAVideoPlayerEvent.Video_Prepared) {
////                    try {
////                        videoPlayer.play(path[0]);
////                    } catch (Throwable throwable) {
////                        // do nothing
////                    }
//////                    boolean op1 = module.startMoatTrackingForVideoPlayer(videoPlayer.getVideoPlayer(), videoPlayer.getMediaPlayer());
//////                    Log.d("SuperAwesome", "Op1 is " + op1);
//////                    assertTrue(op1);
//////                    boolean op2 = module.stopMoatTrackingForVideoPlayer();
//////                    assertTrue(op2);
////                }
////                if (event == SAVideoPlayerEvent.Video_Start) {
////                    Log.d("SuperAwesome", "Started");
////                }
////                if (event == SAVideoPlayerEvent.Video_End) {
////                    Log.d("SuperAwesome", "Ended");
////                }
////            }
////        });
////
////        SAFileDownloader.getInstance().downloadFileFrom(getActivity(), "https://s3-eu-west-1.amazonaws.com/sb-ads-video-transcoded/UAICy6n2MiSfyxmPoPjV4sqWPVXTRjVi.mp4", new SAFileDownloaderInterface() {
////            @Override
////            public void saDidDownloadFile(boolean b, String s) {
////                path[0] = s;
////                manager.beginTransaction().add(R.id.MyCustomVideo, videoPlayer, "VideoPlayer").commit();
////            }
////        });
////
////        sleep(TIMEOUT);
//
//    }
//
//    @UiThreadTest
//    @LargeTest
//    public void test5 () {
//
////        SAAd ad = SAEvents_Aux.getTestAd();
////        SAMoatModule module = new SAMoatModule(getActivity(), ad);
////        module.disableMoatLimiting();
////
////        SAVideoPlayer videoPlayer = null;
////
////        boolean op1 = module.startMoatTrackingForVideoPlayer(null, null);
////        assertTrue(op1);
////        boolean op2 = module.stopMoatTrackingForVideoPlayer();
////        assertTrue(op2);
//    }
//
//    @UiThreadTest
//    @LargeTest
//    public void test6 () {
//
////        SAAd ad = null;
////        SAMoatModule module = new SAMoatModule(getActivity(), ad);
////        module.disableMoatLimiting();
////
////        SAVideoPlayer videoPlayer = null;
////
////        boolean op1 = module.startMoatTrackingForVideoPlayer(null, null);
////        assertFalse(op1);
////        boolean op2 = module.stopMoatTrackingForVideoPlayer();
////        assertFalse(op2);
//    }
//
//    private void sleep(int timeout) {
//        try {
//            Thread.sleep(timeout);
//        } catch (InterruptedException e) {
//            fail("Unexpected Timeout");
//        }
//    }
}
