package in.ajitesh.chatterbox.module;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.ajitesh.chatterbox.api.IApi;
import in.ajitesh.chatterbox.broadcastreceiver.NetworkStatusListener;
import in.ajitesh.chatterbox.interfaces.IChatModel;

/**
 * Created by ajitesh on 12/10/18.
 */

@Module
public class NetworkModule {

    @Singleton
    @Provides
    @Inject
    public NetworkStatusListener providesNetworkListener(IApi api, IChatModel chatModel){
        return new NetworkStatusListener(api, chatModel);
    }
}
