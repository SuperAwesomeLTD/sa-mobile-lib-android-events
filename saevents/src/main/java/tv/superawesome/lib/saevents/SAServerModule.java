package tv.superawesome.lib.saevents;

import android.content.Context;

import tv.superawesome.lib.saevents.events.SAClickEvent;
import tv.superawesome.lib.saevents.events.SAImpressionEvent;
import tv.superawesome.lib.saevents.events.SAPGCloseEvent;
import tv.superawesome.lib.saevents.events.SAPGFailEvent;
import tv.superawesome.lib.saevents.events.SAPGOpenEvent;
import tv.superawesome.lib.saevents.events.SAPGSuccessEvent;
import tv.superawesome.lib.saevents.events.SAViewableImpressionEvent;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.session.SASession;

public class SAServerModule {

    private SAClickEvent                clickEvent = null;
    private SAImpressionEvent           impressionEvent = null;
    private SAViewableImpressionEvent   viewableImpressionEvent = null;
    private SAPGOpenEvent               sapgOpenEvent = null;
    private SAPGCloseEvent              sapgCloseEvent = null;
    private SAPGFailEvent               sapgFailEvent = null;
    private SAPGSuccessEvent            sapgSuccessEvent = null;

    public SAServerModule (Context context, SAAd ad, SASession session) {
        clickEvent = new SAClickEvent(context, ad, session);
        impressionEvent = new SAImpressionEvent(context, ad, session);
        viewableImpressionEvent = new SAViewableImpressionEvent(context, ad, session);
        sapgOpenEvent = new SAPGOpenEvent(context, ad, session);
        sapgCloseEvent = new SAPGCloseEvent(context, ad, session);
        sapgFailEvent = new SAPGFailEvent(context, ad, session);
        sapgSuccessEvent = new SAPGSuccessEvent(context, ad, session);
    }

    public void triggerClickEvent () {
        if (clickEvent != null) {
            clickEvent.triggerEvent();
        }
    }

    public void triggerImpressionEvent () {
        if (impressionEvent != null) {
            impressionEvent.triggerEvent();
        }
    }

    public void triggerViewableImpressionEvent () {
        if (viewableImpressionEvent != null) {
            viewableImpressionEvent.triggerEvent();
        }
    }

    public void triggerPgOpenEvent () {
        if (sapgOpenEvent != null) {
            sapgOpenEvent.triggerEvent();
        }
    }

    public void triggerPgCloseEvent () {
        if (sapgCloseEvent != null) {
            sapgCloseEvent.triggerEvent();
        }
    }

    public void triggerPgFailEvent () {
        if (sapgFailEvent != null) {
            sapgFailEvent.triggerEvent();
        }
    }

    public void triggerPgSuccessEvent () {
        if (sapgSuccessEvent != null) {
            sapgSuccessEvent.triggerEvent();
        }
    }

    public SAClickEvent getClickEvent() {
        return clickEvent;
    }

    public SAImpressionEvent getImpressionEvent() {
        return impressionEvent;
    }

    public SAViewableImpressionEvent getViewableImpressionEvent() {
        return viewableImpressionEvent;
    }

    public SAPGOpenEvent getSapgOpenEvent() {
        return sapgOpenEvent;
    }

    public SAPGCloseEvent getSapgCloseEvent() {
        return sapgCloseEvent;
    }

    public SAPGFailEvent getSapgFailEvent() {
        return sapgFailEvent;
    }

    public SAPGSuccessEvent getSapgSuccessEvent() {
        return sapgSuccessEvent;
    }
}
