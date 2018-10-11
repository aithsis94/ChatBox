package in.ajitesh.chatterbox.model;

/**
 * Created by ajitesh on 11/10/18.
 */

public class MessageInfo {

    private String message;

    private String chatBotName;

    private String chatBotID;

    private String emotion;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getChatBotName ()
    {
        return chatBotName;
    }

    public void setChatBotName (String chatBotName)
    {
        this.chatBotName = chatBotName;
    }

    public String getChatBotID ()
    {
        return chatBotID;
    }

    public void setChatBotID (String chatBotID)
    {
        this.chatBotID = chatBotID;
    }

    public String getEmotion ()
    {
        return emotion;
    }

    public void setEmotion (String emotion)
    {
        this.emotion = emotion;
    }
}
