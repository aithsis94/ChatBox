package in.ajitesh.chatterbox.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ajitesh on 11/10/18.
 */

public class Util {

    public static final String API_KEY = "6nt5d1nJHkqbkphe";
    public static final String CHAT_BOT_ID = "63906";
    public static final String EXTERNAL_ID = "chirag1";

    public static void registerNetworkBroadcastReceiver(Context context, BroadcastReceiver receiver){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(receiver, intentFilter);
    }

    public static boolean isInternetAvailable(Context context) {

        boolean networkConnection = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            networkConnection = true;
        } else {
            networkConnection = false;
        }
        return networkConnection;
    }
}
