package in.ajitesh.chatterbox.interfaces;

import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.RealmResults;

/**
 * Created by ajitesh on 11/10/18.
 */

public interface IChatMessageListener {

    void onInitData(RealmResults<MessageModel> messageModels);

    void onDataChanged();
}
