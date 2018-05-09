package tv.superawesome.lib.saevents;

import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.VideoView;

import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.session.SASession;

public class SAEvents {

    private SAServerModule              serverModule;
    private SAVASTModule                vastModule;
    private SAMoatModule                moatModule;
    private SAViewableModule            viewableModule;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Set & unset the ad needed for triggering events
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAd (Context context, SASession session, SAAd ad) {
        serverModule = new SAServerModule(context, ad, session);
        vastModule = new SAVASTModule(context, ad);
        moatModule = new SAMoatModule(context, ad);
        viewableModule = new SAViewableModule();
    }

    public void unsetAd () {
        serverModule = null;
        vastModule = null;
        moatModule = null;
        viewableModule = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Normal ad events
    ////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Parental Gate Events
    ////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // VAST Events
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getVASTClickThroughEvent () {
        return vastModule != null ? vastModule.getVASTClickThroughEvent() : "";
    }

    public void triggerVASTClickThroughEvent () {
        if (vastModule != null) {
            vastModule.triggerVastClickThroughEvent();
        }
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Viewable impression
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isChildInRect (ViewGroup layout) {
        return viewableModule != null && viewableModule.isChildInRect(layout);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // MOAT
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void startMoatTracking (Application application) {
        SAMoatModule.startMoatTracking(application);
    }

    public String startMoatTrackingForDisplay(WebView view) {
        return moatModule != null ? moatModule.startMoatTrackingForDisplay(view) : "";
    }

    public boolean stopMoatTrackingForDisplay() {
        return moatModule == null || moatModule.stopMoatTrackingForDisplay();
    }

    public boolean startMoatTrackingForVideoPlayer(VideoView videoView, int duration){
        return moatModule == null || moatModule.startMoatTrackingForVideoPlayer(videoView, duration);
    }

    public boolean sendMoatPlayingEvent (int position) {
        return moatModule == null || moatModule.sendPlayingEvent(position);
    }

    public boolean sendMoatStartEvent (int position) {
        return moatModule == null || moatModule.sendStartEvent(position);
    }

    public boolean sendMoatFirstQuartileEvent (int position) {
        return moatModule == null || moatModule.sendFirstQuartileEvent(position);
    }

    public boolean sendMoatMidpointEvent (int position) {
        return moatModule == null || moatModule.sendMidpointEvent(position);
    }

    public boolean sendMoatThirdQuartileEvent (int position) {
        return moatModule == null || moatModule.sendThirdQuartileEvent(position);
    }

    public boolean sendMoatCompleteEvent (int position) {
        return moatModule == null || moatModule.sendCompleteEvent(position);
    }

    public boolean stopMoatTrackingForVideoPlayer() {
        return moatModule == null || moatModule.stopMoatTrackingForVideoPlayer();
    }

    public void disableMoatLimiting () {
        if (moatModule != null) {
            moatModule.disableMoatLimiting();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public SAServerModule getServerModule() {
        return serverModule;
    }

    public SAVASTModule getVastModule() {
        return vastModule;
    }

    public SAMoatModule getMoatModule() {
        return moatModule;
    }

    public SAViewableModule getViewableModule() {
        return viewableModule;
    }
}
