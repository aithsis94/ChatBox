package in.ajitesh.chatterbox.viewholder;

import android.view.View;

import in.ajitesh.chatterbox.model.Message;
import in.ajitesh.chatterbox.model.db.MessageModel;

/**
 * Created by ajitesh on 11/10/18.
 */

public class FromMessageViewHolder extends MessageViewHolder {

    public FromMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void attachToView(MessageModel message) {
        this.messageTextView.setText(message.getMessage());
    }
}
