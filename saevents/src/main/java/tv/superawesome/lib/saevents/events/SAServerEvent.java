package tv.superawesome.lib.saevents.events;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sanetwork.request.SANetwork;
import tv.superawesome.lib.sanetwork.request.SANetworkInterface;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAServerEvent {

    protected Context     context = null;
    protected SAAd        ad = null;
    protected SASession   session = null;
    private   SANetwork   network = null;

    public SAServerEvent(Context context, SAAd ad, SASession session) {
        this.context = context;
        this.ad = ad;
        this.session = session;
        this.network = new SANetwork();
    }

    public String getUrl () {
        try {
            return session.getBaseUrl();
        } catch (Exception e) {
            return null;
        }
    }

    public String getEndpoint () {
        return "";
    }

    public JSONObject getHeader () {
        return SAJsonParser.newObject(new Object[] {
                "Content-Type", "application/json",
                "User-Agent", SAUtils.getUserAgent(context)
        });
    }

    public JSONObject getQuery () {
        return new JSONObject();
    }

    public void triggerEvent () {

        network.sendGET(context, getUrl() + getEndpoint(), getQuery(), getHeader(), new SANetworkInterface() {
            @Override
            public void saDidGetResponse(int status, String payload, boolean success) {
                // do nothing
            }
        });

    }

    public void triggerEvent (final Listener listener) {

        network.sendGET(context, getUrl() + getEndpoint(), getQuery(), getHeader(), new SANetworkInterface() {
            @Override
            public void saDidGetResponse(int status, String payload, boolean success) {

                if ((status == 200 || status == 302) && success) {
                    if (listener != null) {
                        listener.didTriggerEvent(true);
                    }
                } else {
                    if (listener != null) {
                        listener.didTriggerEvent(false);
                    }
                }
            }
        });

    }

    public interface Listener {
        void didTriggerEvent (boolean success);
    }

}
