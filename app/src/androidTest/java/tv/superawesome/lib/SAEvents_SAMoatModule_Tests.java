package tv.superawesome.lib;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.webkit.WebView;

import tv.superawesome.lib.saevents.SAMoatModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.savideoplayer.SAVideoPlayer;

public class SAEvents_SAMoatModule_Tests extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAMoatModule_Tests() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @LargeTest
    public void test1 () {

        SAAd ad = SAEvents_Aux.getTestAd();
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        WebView webView = (WebView) getActivity().findViewById(R.id.TestWebView);

        String moat = module.registerDisplayMoatEvent(getActivity(), webView);
        assertNotNull(moat);
        assertTrue(moat.contains("z.moatads.com"));
        assertTrue(moat.contains("superawesomeinappdisplay"));
        assertTrue(moat.contains("731223424656"));
        assertTrue(moat.contains("moatClientLevel1="+ad.advertiserId));
        assertTrue(moat.contains("moatClientLevel2="+ad.campaignId));
        assertTrue(moat.contains("moatClientLevel3="+ad.lineItemId));
        assertTrue(moat.contains("moatClientLevel4="+ad.creative.id));
        assertTrue(moat.contains("moatClientSlicer1="+ad.appId));
        assertTrue(moat.contains("moatClientSlicer2="+ad.placementId));
        assertTrue(moat.contains("moatClientSlicer3="+ad.publisherId));

        boolean operation = module.unregisterDisplayMoatEvent();
        assertTrue(operation);
    }

    @LargeTest
    public void test2 () {

        SAAd ad = SAEvents_Aux.getTestAd();
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        WebView webView = null;

        String moat = module.registerDisplayMoatEvent(getActivity(), webView);
        assertNotNull(moat);
        assertTrue(moat.contains("z.moatads.com"));
        assertTrue(moat.contains("superawesomeinappdisplay"));
        assertTrue(moat.contains("731223424656"));
        assertTrue(moat.contains("moatClientLevel1="+ad.advertiserId));
        assertTrue(moat.contains("moatClientLevel2="+ad.campaignId));
        assertTrue(moat.contains("moatClientLevel3="+ad.lineItemId));
        assertTrue(moat.contains("moatClientLevel4="+ad.creative.id));
        assertTrue(moat.contains("moatClientSlicer1="+ad.appId));
        assertTrue(moat.contains("moatClientSlicer2="+ad.placementId));
        assertTrue(moat.contains("moatClientSlicer3="+ad.publisherId));

        boolean operation = module.unregisterDisplayMoatEvent();
        assertTrue(operation);
    }

    @LargeTest
    public void test3 () {

        SAAd ad = null;
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        WebView webView = null;

        String moat = module.registerDisplayMoatEvent(getActivity(), webView);
        assertNotNull(moat);
        assertTrue(moat.isEmpty());

        boolean operation = module.unregisterDisplayMoatEvent();
        assertFalse(operation);
    }

    @LargeTest
    public void test4 () {

        SAAd ad = SAEvents_Aux.getTestAd();
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        SAVideoPlayer videoPlayer = (SAVideoPlayer) getActivity().getFragmentManager().findFragmentById(R.id.TestVideoPlayer);

        boolean op1 = module.registerVideoMoatEvent(getActivity(), videoPlayer.getVideoPlayer(), videoPlayer.getMediaPlayer());
        assertTrue(op1);
        boolean op2 = module.unregisterVideoMoatEvent();
        assertTrue(op2);
    }

    @LargeTest
    public void test5 () {

        SAAd ad = SAEvents_Aux.getTestAd();
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        SAVideoPlayer videoPlayer = null;

        boolean op1 = module.registerVideoMoatEvent(getActivity(), null, null);
        assertTrue(op1);
        boolean op2 = module.unregisterVideoMoatEvent();
        assertTrue(op2);
    }

    @LargeTest
    public void test6 () {

        SAAd ad = null;
        SAMoatModule module = new SAMoatModule(ad);
        module.disableMoatLimiting();

        SAVideoPlayer videoPlayer = null;

        boolean op1 = module.registerVideoMoatEvent(getActivity(), null, null);
        assertFalse(op1);
        boolean op2 = module.unregisterVideoMoatEvent();
        assertFalse(op2);
    }
}
