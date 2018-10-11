package in.ajitesh.chatterbox.model.db;

import io.realm.RealmObject;

/**
 * Created by ajitesh on 11/10/18.
 */

public class MessageModel extends RealmObject {

    private long identity;

    private int messageType;

    private String message;

    private boolean isSynced;

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
}
