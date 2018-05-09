package tv.superawesome.lib.saevents.events;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.saad.SAAd;
import tv.superawesome.lib.sasession.session.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAPGCloseEvent extends SAServerEvent {

    public SAPGCloseEvent(Context context, SAAd ad, SASession session) {
        super(context, ad, session);
    }

    @Override
    public String getEndpoint() {
        return "/event";
    }

    @Override
    public JSONObject getQuery() {
        try {
            return SAJsonParser.newObject(
                    "sdkVersion", session.getVersion(),
                    "ct", session.getConnectionType(),
                    "bundle", session.getPackageName(),
                    "rnd", session.getCachebuster(),
                    "data", SAUtils.encodeDictAsJsonDict(SAJsonParser.newObject(
                            "placement", ad.placementId,
                            "line_item", ad.lineItemId,
                            "creative", ad.creative.id,
                            "type", "parentalGateClose")));
        } catch (Exception e) {
            return new JSONObject();
        }
    }
}
