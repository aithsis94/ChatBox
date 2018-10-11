package in.ajitesh.chatterbox.interfaces;

import android.content.Context;

/**
 * Created by ajitesh on 11/10/18.
 */

public interface IChatPresenter {

    void injectView(IChatView view);

    void sendMessage(Context context, String message);
}
