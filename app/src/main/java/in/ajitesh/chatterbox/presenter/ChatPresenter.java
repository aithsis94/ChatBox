package in.ajitesh.chatterbox.presenter;

import android.content.Context;

import in.ajitesh.chatterbox.Utils.Util;
import in.ajitesh.chatterbox.api.IApi;
import in.ajitesh.chatterbox.api.IChatApi;
import in.ajitesh.chatterbox.interfaces.IChatMessageListener;
import in.ajitesh.chatterbox.interfaces.IChatModel;
import in.ajitesh.chatterbox.interfaces.IChatPresenter;
import in.ajitesh.chatterbox.interfaces.IChatView;
import in.ajitesh.chatterbox.model.ChatBoxReplyMessage;
import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajitesh on 11/10/18.
 */

public class ChatPresenter implements IChatPresenter {

    private IChatView view;
    private IApi api;
    private IChatModel chatModel;

    public ChatPresenter(IApi api, IChatModel chatModel) {
        this.api = api;
        this.chatModel = chatModel;
    }

    @Override
    public void injectView(IChatView view) {
        this.view = view;
        this.chatModel.registerListener(new ChatMessageListener());
    }

    @Override
    public void sendMessage(Context context, String messageString) {

        if (isBlank(messageString))
            return;

        Message fromMessage = new Message(Message.TYPE_FROM_MESSAGE, messageString);
        final long fromMessageId = this.chatModel.addMessge(fromMessage);

        if (Util.isInternetAvailable(context)) {

            IChatApi chatApi = api.getChatApi();
            Call<ChatBoxReplyMessage> messageCall = chatApi.exchangeMessage(messageString, Util.CHAT_BOT_ID, Util.EXTERNAL_ID, Util.API_KEY);
            messageCall.enqueue(new Callback<ChatBoxReplyMessage>() {

                @Override
                public void onResponse(Call<ChatBoxReplyMessage> call, Response<ChatBoxReplyMessage> response) {

                    ChatBoxReplyMessage replyMessage = response.body();

                    Message toMessage = new Message(Message.TYPE_TO_MESSAGE, replyMessage.getMessage().getMessage());
                    chatModel.addMessge(toMessage);
                    chatModel.markMessageAsSynced(fromMessageId);
                }

                @Override
                public void onFailure(Call<ChatBoxReplyMessage> call, Throwable t) {
                    if (view != null) {
                        view.showMessage(t.getMessage(), true);
                    }
                }
            });
        } else {
            view.showMessage("no internet", false);
        }
    }

    class ChatMessageListener implements IChatMessageListener {

        @Override
        public void onInitData(RealmResults<MessageModel> messageModels) {
            if (view != null) {
                view.loadChatData(messageModels);
            }
        }

        @Override
        public void onDataChanged() {
            if (view != null) {
                view.onChatDataUpdated();
            }
        }
    }

    private static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
}
