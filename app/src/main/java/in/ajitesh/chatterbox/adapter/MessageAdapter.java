package in.ajitesh.chatterbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.ajitesh.chatterbox.R;
import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;
import in.ajitesh.chatterbox.viewholder.FromMessageViewHolder;
import in.ajitesh.chatterbox.viewholder.MessageViewHolder;
import in.ajitesh.chatterbox.viewholder.ToMessageViewHolder;
import io.realm.RealmResults;

/**
 * Created by ajitesh on 11/10/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{

    private LayoutInflater inflater;
    private RealmResults<MessageModel> messageList;

    public MessageAdapter(LayoutInflater inflater, RealmResults<MessageModel> messageList) {
        this.inflater = inflater;
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        return this.messageList.get(position).getMessageType();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == Message.TYPE_FROM_MESSAGE){
            View view = inflater.inflate(R.layout.layout_list_item_to_message, parent, false);
            return new ToMessageViewHolder(view);
        }else if(viewType == Message.TYPE_TO_MESSAGE){
            View view = inflater.inflate(R.layout.layout_list_item_from_message, parent, false);
            return new FromMessageViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageModel message = this.messageList.get(position);
        holder.attachToView(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
