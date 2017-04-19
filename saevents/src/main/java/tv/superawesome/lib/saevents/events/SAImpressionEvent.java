package tv.superawesome.lib.saevents.events;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.SASession;

public class SAImpressionEvent extends SAServerEvent {

    public SAImpressionEvent(Context context, SAAd ad, SASession session) {
        super(context, ad, session);
    }

    @Override
    public String getEndpoint() {
        return "/impression";
    }

    @Override
    public JSONObject getQuery() {
        try {
            return SAJsonParser.newObject(
                    "placement", ad.placementId,
                    "creative", ad.creative.id,
                    "line_item", ad.lineItemId,
                    "sdkVersion", session.getVersion(),
                    "bundle", session.getPackageName(),
                    "ct", session.getConnectionType(),
                    "no_image", true,
                    "rnd", session.getCachebuster());
        } catch (Exception e) {
            return new JSONObject();
        }
    }
}
