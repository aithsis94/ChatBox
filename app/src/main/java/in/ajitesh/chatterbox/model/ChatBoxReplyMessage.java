package in.ajitesh.chatterbox.model;

/**
 * Created by ajitesh on 11/10/18.
 */

public class ChatBoxReplyMessage {

    private MessageInfo message;

    private String errorMessage;

    private String success;

    public MessageInfo getMessage ()
    {
        return message;
    }

    public void setMessage (MessageInfo message)
    {
        this.message = message;
    }

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }
}
