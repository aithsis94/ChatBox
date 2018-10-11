package in.ajitesh.chatterbox.model;

import in.ajitesh.chatterbox.interfaces.IChatModel;
import in.ajitesh.chatterbox.interfaces.IChatMessageListener;
import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by ajitesh on 11/10/18.
 */

public class DbBackedChatModel implements IChatModel {

    private Realm realm;
    private IChatMessageListener listener;
    private RealmResults<MessageModel> messageRealmResults;

    public DbBackedChatModel(){
        this.realm = Realm.getDefaultInstance();
        this.init();
    }

    public void init(){

        this.dispose();
        this.messageRealmResults = realm.where(MessageModel.class).sort("identity", Sort.ASCENDING).findAll();
        messageRealmResults.addChangeListener(new RealmChangeListener<RealmResults<MessageModel>>() {

            @Override
            public void onChange(RealmResults<MessageModel> messageModels) {

                if(listener != null)
                    listener.onDataChanged();
            }
        });
    }

    @Override
    public void dispose() {
        Realm realm = Realm.getDefaultInstance();
        realm.removeAllChangeListeners();
    }

    @Override
    public void registerListener(IChatMessageListener listener) {

        this.listener = listener;

        if(this.listener != null){
            this.listener.onInitData(this.messageRealmResults);
        }
    }

    @Override
    public synchronized long addMessge(Message message) {

        long nextId = 1;

        realm.beginTransaction();
        {
            Number number = realm.where(MessageModel.class).max("identity");

            if(number != null){
                nextId = number.longValue() + 1;
            }

            MessageModel messageModel = this.realm.createObject(MessageModel.class);
            messageModel.setMessageType(message.getType());
            messageModel.setMessage(message.getMessage());
            messageModel.setIdentity(nextId);
            messageModel.setSynced(message.getType() == Message.TYPE_TO_MESSAGE ? true : false);
        }
        realm.commitTransaction();

        return nextId;
    }

    @Override
    public void markMessageAsSynced(long id) {

        realm.beginTransaction();
        {
            MessageModel messageModel = realm.where(MessageModel.class).equalTo("identity", id).findFirst();
            messageModel.setSynced(true);
        }
        realm.commitTransaction();
    }
}
