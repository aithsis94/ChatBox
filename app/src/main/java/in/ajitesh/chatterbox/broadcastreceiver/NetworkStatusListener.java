package in.ajitesh.chatterbox.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import in.ajitesh.chatterbox.Utils.Util;
import in.ajitesh.chatterbox.api.IApi;
import in.ajitesh.chatterbox.api.IChatApi;
import in.ajitesh.chatterbox.interfaces.IChatModel;
import in.ajitesh.chatterbox.model.ChatBoxReplyMessage;
import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ajitesh on 11/10/18.
 */


public class NetworkStatusListener extends BroadcastReceiver {

    private static boolean isRunning = false;

    private IApi api;
    private IChatModel chatModel;

    public NetworkStatusListener(IApi api, IChatModel chatModel) {
        this.api = api;
        this.chatModel = chatModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Util.isInternetAvailable(context)) {
            onInternetConnected();
        }
    }

    public synchronized void onInternetConnected() {

        if (isRunning)
            return;

        isRunning = true;

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                isRunning = false;
            }

            @Override
            protected Void doInBackground(Void... voids) {


                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                {
                    RealmResults<MessageModel> messageModels = realm.where(MessageModel.class).equalTo("isSynced", false)
                            .equalTo("messageType", Message.TYPE_FROM_MESSAGE).findAll();

                    IChatApi chatApi = api.getChatApi();

                    for (int i = 0; i < messageModels.size(); i++) {

                        try {

                            final MessageModel fromMessageModel = messageModels.get(i);

                            Call<ChatBoxReplyMessage> messageCall = chatApi.exchangeMessage(fromMessageModel.getMessage(), Util.CHAT_BOT_ID, Util.EXTERNAL_ID, Util.API_KEY);
                            Response<ChatBoxReplyMessage> replyMessage = messageCall.execute();

                            ChatBoxReplyMessage reply = replyMessage.body();

                            final Message toMessage = new Message(Message.TYPE_TO_MESSAGE, reply.getMessage().getMessage());

                            hostingActivity.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    chatModel.addMessge(toMessage);
                                }
                            });

                            fromMessageModel.setSynced(true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
                realm.commitTransaction();

                return null;
            }
        }.execute();
    }

    private Activity hostingActivity;

    public void setHostingActivity(Activity hostingActivity) {
        this.hostingActivity = hostingActivity;
    }
}
