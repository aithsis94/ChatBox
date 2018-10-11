package in.ajitesh.chatterbox.model;

/**
 * Created by ajitesh on 11/10/18.
 */

public class Message {

    public static final int TYPE_FROM_MESSAGE = 1;
    public static final int TYPE_TO_MESSAGE = 2;

    private final int type;
    private final String message;

    public Message(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
