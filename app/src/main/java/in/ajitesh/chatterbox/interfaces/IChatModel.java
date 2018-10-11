package in.ajitesh.chatterbox.interfaces;

import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.RealmResults;

/**
 * Created by ajitesh on 11/10/18.
 */

public interface IChatModel {

    void registerListener(IChatMessageListener listener);

    void dispose();

    long addMessge(Message message);

    void markMessageAsSynced(long id);
}
