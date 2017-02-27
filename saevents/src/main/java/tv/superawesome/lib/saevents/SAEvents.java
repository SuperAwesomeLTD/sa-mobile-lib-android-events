package tv.superawesome.lib.saevents;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.VideoView;

import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;

public class SAEvents {

    private SAServerModule              serverModule;
    private SAVASTModule                vastModule;
    private SAMoatModule                moatModule;
    private SAViewableModule            viewableModule;

    public void setAd (Context context, SASession session, SAAd ad) {
        serverModule = new SAServerModule(context, ad, session);
        vastModule = new SAVASTModule(context, ad);
        moatModule = new SAMoatModule(ad);
        viewableModule = new SAViewableModule(ad);
    }

    public void unsetAd () {
        serverModule = null;
        vastModule = null;
        moatModule = null;
        viewableModule = null;
    }

    public void triggerClickEvent () {
        if (serverModule != null) {
            serverModule.triggerClickEvent();
        }
    }

    public void triggerImpressionEvent () {
        if (serverModule != null) {
            serverModule.triggerImpressionEvent();
        }
    }

    public void triggerViewableImpressionEvent () {
        if (serverModule != null) {
            serverModule.triggerViewableImpressionEvent();
        }
    }

    public void triggerPgOpenEvent () {
        if (serverModule != null) {
            serverModule.triggerPgOpenEvent();
        }
    }

    public void triggerPgCloseEvent () {
        if (serverModule != null) {
            serverModule.triggerPgCloseEvent();
        }
    }

    public void triggerPgFailEvent () {
        if (serverModule != null) {
            serverModule.triggerPgFailEvent();
        }
    }

    public void triggerPgSuccessEvent () {
        if (serverModule != null) {
            serverModule.triggerPgSuccessEvent();
        }
    }

    public String getVASTClickThroughEvent () {
        return vastModule != null ? vastModule.getVASTClickThroughEvent() : "";
    }

    public void triggerVASTErrorEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTErrorEvent();
        }
    }

    public void triggerVASTImpressionEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTImpressionEvent();
        }
    }

    public void triggerVASTCreativeViewEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTCreativeViewEvent();
        }
    }

    public void triggerVASTStartEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTStartEvent();
        }
    }

    public void triggerVASTFirstQuartileEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTFirstQuartileEvent();
        }
    }

    public void triggerVASTMidpointEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTMidpointEvent();
        }
    }

    public void triggerVASTThirdQuartileEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTThirdQuartileEvent();
        }
    }

    public void triggerVASTCompleteEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTCompleteEvent();
        }
    }

    public void triggerVASTClickTrackingEvent () {
        if (vastModule != null) {
            vastModule.triggerVASTClickTrackingEvent();
        }
    }

    public void checkViewableStatusForDisplay (ViewGroup layout, SAViewableModule.Listener listener) {
        if (viewableModule != null) {
            viewableModule.checkViewableStatusForDisplay (layout, listener);
        }
    }

    public void checkViewableStatusForVideo (ViewGroup layout, SAViewableModule.Listener listener) {
        if (viewableModule != null) {
            viewableModule.checkViewableStatusForVideo (layout, listener);
        }

    }

    public String registerDisplayMoatEvent(Activity activity, WebView view) {
        return moatModule != null ? moatModule.registerDisplayMoatEvent(activity, view) : "";
    }

    public boolean unregisterDisplayMoatEvent() {
        return moatModule == null || moatModule.unregisterDisplayMoatEvent();
    }

    public boolean registerVideoMoatEvent(Activity activity, VideoView video, MediaPlayer mp){
        return moatModule == null || moatModule.registerVideoMoatEvent(activity, video, mp);
    }

    public boolean unregisterVideoMoatEvent() {
        return moatModule == null || moatModule.unregisterVideoMoatEvent();
    }

    public void disableMoatLimiting () {
        if (moatModule != null) {
            moatModule.disableMoatLimiting();
        }
    }
}
