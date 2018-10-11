package in.ajitesh.chatterbox.interfaces;

import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.RealmResults;

/**
 * Created by ajitesh on 11/10/18.
 */

public interface IChatView {

    void loadChatData(RealmResults<MessageModel> messageModels);

    void onChatDataUpdated();

    void showMessage(String errorMessage, boolean isLongLivedMessage);

    void onSendMessageClicked();
}
