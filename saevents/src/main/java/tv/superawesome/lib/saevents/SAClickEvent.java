package tv.superawesome.lib.saevents;

import android.content.Context;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.samodelspace.SAAd;
import tv.superawesome.lib.samodelspace.SACreativeFormat;
import tv.superawesome.lib.sasession.SASession;

public class SAClickEvent extends SAServerEvent {

    public SAClickEvent(Context context, SAAd ad, SASession session) {
        super(context, ad, session);
    }

    @Override
    public String getEndpoint() {
        return ad.creative.format == SACreativeFormat.video ? "/video/click" : "/click";
    }

    @Override
    public JSONObject getQuery () {
        return SAJsonParser.newObject(new Object[] {
                "placement", ad.placementId,
                "bundle", session.getPackageName(),
                "creative", ad.creative.id,
                "line_item", ad.lineItemId,
                "ct", session.getConnectionType(),
                "sdkVersion", session.getVersion(),
                "rnd", session.getCachebuster()
        });
    }
}
