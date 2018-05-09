package tv.superawesome.lib.saevents;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import tv.superawesome.lib.saevents.events.SAClickEvent;
import tv.superawesome.lib.saevents.events.SAImpressionEvent;
import tv.superawesome.lib.saevents.events.SAPGCloseEvent;
import tv.superawesome.lib.saevents.events.SAPGFailEvent;
import tv.superawesome.lib.saevents.events.SAPGOpenEvent;
import tv.superawesome.lib.saevents.events.SAPGSuccessEvent;
import tv.superawesome.lib.saevents.events.SAURLEvent;
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
        this(context, ad, session, Executors.newSingleThreadExecutor(), 15000);
    }

    public SAServerModule (Context context, SAAd ad, SASession session, Executor executor, int timeout) {
        clickEvent = new SAClickEvent(context, ad, session, executor, timeout);
        impressionEvent = new SAImpressionEvent(context, ad, session, executor, timeout);
        viewableImpressionEvent = new SAViewableImpressionEvent(context, ad, session, executor, timeout);
        sapgOpenEvent = new SAPGOpenEvent(context, ad, session, executor, timeout);
        sapgCloseEvent = new SAPGCloseEvent(context, ad, session, executor, timeout);
        sapgFailEvent = new SAPGFailEvent(context, ad, session, executor, timeout);
        sapgSuccessEvent = new SAPGSuccessEvent(context, ad, session, executor, timeout);
    }

    public void triggerClickEvent (SAURLEvent.Listener listener) {
        if (clickEvent != null) {
            clickEvent.triggerEvent(listener);
        }
    }

    public void triggerImpressionEvent (SAURLEvent.Listener listener) {
        if (impressionEvent != null) {
            impressionEvent.triggerEvent(listener);
        }
    }

    public void triggerViewableImpressionEvent (SAURLEvent.Listener listener) {
        if (viewableImpressionEvent != null) {
            viewableImpressionEvent.triggerEvent(listener);
        }
    }

    public void triggerPgOpenEvent (SAURLEvent.Listener listener) {
        if (sapgOpenEvent != null) {
            sapgOpenEvent.triggerEvent(listener);
        }
    }

    public void triggerPgCloseEvent (SAURLEvent.Listener listener) {
        if (sapgCloseEvent != null) {
            sapgCloseEvent.triggerEvent(listener);
        }
    }

    public void triggerPgFailEvent (SAURLEvent.Listener listener) {
        if (sapgFailEvent != null) {
            sapgFailEvent.triggerEvent(listener);
        }
    }

    public void triggerPgSuccessEvent (SAURLEvent.Listener listener) {
        if (sapgSuccessEvent != null) {
            sapgSuccessEvent.triggerEvent(listener);
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
