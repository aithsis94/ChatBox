package in.ajitesh.chatterbox.module;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.ajitesh.chatterbox.api.IApi;
import in.ajitesh.chatterbox.interfaces.IChatModel;
import in.ajitesh.chatterbox.interfaces.IChatPresenter;
import in.ajitesh.chatterbox.model.DbBackedChatModel;
import in.ajitesh.chatterbox.presenter.ChatPresenter;

/**
 * Created by ajitesh on 11/10/18.
 */

@Module
public class ChatDataModule {

    @Singleton
    @Provides
    public IChatModel providesChatModel(){
       return new DbBackedChatModel();
    }

    @Singleton
    @Provides
    @Inject
    public IChatPresenter providesChatPresenter(IApi api, IChatModel chatModel){
        return new ChatPresenter(api, chatModel);
    }
}
