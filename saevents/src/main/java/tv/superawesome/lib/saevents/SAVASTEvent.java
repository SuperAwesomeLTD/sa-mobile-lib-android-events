package tv.superawesome.lib.saevents;

import android.content.Context;

public class SAVASTEvent extends SAServerEvent {

    protected String vastUrl = null;

    public SAVASTEvent (Context context, String vastUrl) {
        super(context, null, null);
        this.vastUrl = vastUrl;
    }

    @Override
    public String getUrl() {
        return vastUrl;
    }

    @Override
    public String getEndpoint() {
        return "";
    }
}
