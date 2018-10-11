package in.ajitesh.chatterbox.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ajitesh.chatterbox.R;
import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;

/**
 * Created by ajitesh on 11/10/18.
 */

public abstract class MessageViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.textViewMessage)
    TextView messageTextView;

    public MessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void attachToView(MessageModel message);
}
