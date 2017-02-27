package tv.superawesome.lib.saevents.events;

import android.content.Context;

public class SAURLEvent extends SAServerEvent {

    protected String vastUrl = null;

    public SAURLEvent(Context context, String vastUrl) {
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
