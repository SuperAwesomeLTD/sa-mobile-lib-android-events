package tv.superawesome.lib.saevents;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.sasession.SASession;
import tv.superawesome.lib.sautils.SAUtils;

public class SAViewableImpressionEvent extends SAServerEvent {

    public SAViewableImpressionEvent(Context context, SAAd ad, SASession session) {
        super(context, ad, session);
    }

    @Override
    public String getEndpoint() {
        return "/event";
    }

    @Override
    public JSONObject getQuery() {
        return SAJsonParser.newObject(new Object[] {
                "sdkVersion", session.getVersion(),
                "ct", session.getConnectionType(),
                "bundle", session.getPackageName(),
                "rnd", session.getCachebuster(),
                "data", SAUtils.encodeDictAsJsonDict(SAJsonParser.newObject(new Object[]{
                        "placement", ad.placementId,
                        "line_item", ad.lineItemId,
                        "creative", ad.creative.id,
                        "type", "viewable_impression",
                }))
        });
    }
}
