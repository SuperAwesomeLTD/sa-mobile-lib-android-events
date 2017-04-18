package tv.superawesome.lib.saevents.events;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.samodelspace.saad.SACreativeFormat;
import tv.superawesome.lib.sasession.SASession;

public class SAClickEvent extends SAServerEvent {

    public SAClickEvent(Context context, SAAd ad, SASession session) {
        super(context, ad, session);
    }

    @Override
    public String getEndpoint() {
        return ad != null && ad.creative != null ? ad.creative.format == SACreativeFormat.video ? "/video/click" : "/click" : "";
    }

    @Override
    public JSONObject getQuery () {
        try {
            return SAJsonParser.newObject(new Object[]{
                    "placement", ad.placementId,
                    "bundle", session.getPackageName(),
                    "creative", ad.creative.id,
                    "line_item", ad.lineItemId,
                    "ct", session.getConnectionType(),
                    "sdkVersion", session.getVersion(),
                    "rnd", session.getCachebuster()
            });
        } catch (Exception e) {
            return new JSONObject();
        }
    }
}
