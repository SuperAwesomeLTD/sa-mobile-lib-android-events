package tv.superawesome.lib;

import android.app.Application;
import android.util.Log;

import tv.superawesome.lib.saevents.SAEvents;

/**
 * Created by gabriel.coman on 30/04/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SuperAwesome", "On Create / MyApplication");
        SAEvents.initMoat(this, true);
    }
}
