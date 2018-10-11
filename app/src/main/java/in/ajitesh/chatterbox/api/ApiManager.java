package in.ajitesh.chatterbox.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ajitesh on 11/10/18.
 */

public class ApiManager implements IApi {

    private IChatApi chatApi;

    private ApiManager(IChatApi chatApi){
        this.chatApi = chatApi;
    }

    public static ApiManager getApiManager(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.personalityforge.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IChatApi chatApi = retrofit.create(IChatApi.class);
        ApiManager apiManager = new ApiManager(chatApi);
        return apiManager;
    }

    @Override
    public IChatApi getChatApi() {
        return chatApi;
    }
}
