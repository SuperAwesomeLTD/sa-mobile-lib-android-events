package tv.superawesome.lib;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import tv.superawesome.lib.saevents.SAMoatModule;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sanetwork.file.SAFileDownloader;
import tv.superawesome.lib.sanetwork.file.SAFileDownloaderInterface;
import tv.superawesome.lib.savideoplayer.SAVideoPlayer;
import tv.superawesome.lib.savideoplayer.SAVideoPlayerEvent;
import tv.superawesome.lib.savideoplayer.SAVideoPlayerEventInterface;

public class SAEvents_SAMoatModule_Tests2 extends ActivityInstrumentationTestCase2<MainActivity> {

    public SAEvents_SAMoatModule_Tests2() {
        super("tv.superawesome.lib", MainActivity.class);
    }

    @UiThreadTest
    @LargeTest
    public void test1 () {

        SAAd ad = SAEvents_Aux.getTestAd();
        final SAMoatModule module = new SAMoatModule(getActivity(), ad);
        module.disableMoatLimiting();

        final SAVideoPlayer videoPlayer = new SAVideoPlayer();
        final FragmentManager manager = getActivity().getFragmentManager();
        final String[] path = {};

        videoPlayer.setEventListener(new SAVideoPlayerEventInterface() {
            @Override
            public void saVideoPlayerDidReceiveEvent(SAVideoPlayerEvent event, int i, int i1) {
                if (event == SAVideoPlayerEvent.Video_Prepared) {
                    try {
                        videoPlayer.play(path[0]);
                    } catch (Throwable throwable) {
                        // do nothing
                    }
                }
                if (event == SAVideoPlayerEvent.Video_Start) {
                    Log.d("SuperAwesome", "Started");
                }
                if (event == SAVideoPlayerEvent.Video_End) {
                    Log.d("SuperAwesome", "Ended");
                }
            }
        });

        String url = "https://sa-beta-ads-video-transcoded-superawesome.netdna-ssl.com/5E827ejOz2QYaRWqyJpn15r1NyvInPy9.mp4";
        SAFileDownloader.getInstance().downloadFileFrom(getActivity(), url, new SAFileDownloaderInterface() {
            @Override
            public void saDidDownloadFile(boolean b, String s) {
                assertTrue(b);
                path[0] = s;
                manager.beginTransaction().add(R.id.MyCustomVideo, videoPlayer, "VideoPlayer").commit();
            }
        });

        sleep(35000);

    }
    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            fail("Unexpected Timeout");
        }
    }

}
