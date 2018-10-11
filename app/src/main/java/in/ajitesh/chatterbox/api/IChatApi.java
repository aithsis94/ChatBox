package in.ajitesh.chatterbox.api;

import in.ajitesh.chatterbox.model.ChatBoxReplyMessage;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ajitesh on 11/10/18.
 */

public interface IChatApi {

    @GET("api/chat/")
    Call<ChatBoxReplyMessage> exchangeMessage(@Query("message") String message,
                                              @Query("chatBotID") String chatBoxId,
                                              @Query("externalID") String externalId,
                                              @Query("apiKey") String apiKey);
}
