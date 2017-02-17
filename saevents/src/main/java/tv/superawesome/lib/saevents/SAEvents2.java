package tv.superawesome.lib.saevents;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.sasession.SASession;

public class SAEvents2 {

    private Context   context = null;

    private SAAd      ad      = null;
    private SASession session = null;

    private SAClickEvent clickEvent = null;
    private SAImpressionEvent impressionEvent = null;
    private SAViewableImpressionEvent viewableImpressionEvent = null;
    private SAPGOpenEvent sapgOpenEvent = null;
    private SAPGCloseEvent sapgCloseEvent = null;
    private SAPGFailEvent sapgFailEvent = null;
    private SAPGSuccessEvent sapgSuccessEvent = null;

    private SAVASTEvent vastClickThrough = null;
    private List<SAVASTEvent> vastError = new ArrayList<>();
    private List<SAVASTEvent> vastImpression = new ArrayList<>();
    private List<SAVASTEvent> vastCreativeView = new ArrayList<>();
    private List<SAVASTEvent> vastStart = new ArrayList<>();
    private List<SAVASTEvent> vastFirstQuartile = new ArrayList<>();
    private List<SAVASTEvent> vastMidpoint = new ArrayList<>();
    private List<SAVASTEvent> vastThirdQuartile = new ArrayList<>();
    private List<SAVASTEvent> vastComplete = new ArrayList<>();
    private List<SAVASTEvent> vastClickTracking = new ArrayList<>();

    public SAEvents2 (Context context, SASession session) {
        this.context = context;
        this.session = session;
    }

    public void setAd (SAAd ad) {
        this.ad = ad;
    }

}
