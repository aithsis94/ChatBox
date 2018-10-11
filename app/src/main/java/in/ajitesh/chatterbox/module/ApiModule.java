package in.ajitesh.chatterbox.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.ajitesh.chatterbox.api.ApiManager;
import in.ajitesh.chatterbox.api.IApi;

/**
 * Created by ajitesh on 11/10/18.
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    public IApi providesApiModule(){
        IApi api = ApiManager.getApiManager();
        return api;
    }
}
