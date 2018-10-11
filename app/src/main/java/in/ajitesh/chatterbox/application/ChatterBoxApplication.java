package in.ajitesh.chatterbox.application;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ajitesh on 11/10/18.
 */

public class ChatterBoxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
