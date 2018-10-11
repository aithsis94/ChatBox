package in.ajitesh.chatterbox.component;

import javax.inject.Singleton;

import dagger.Component;
import in.ajitesh.chatterbox.broadcastreceiver.NetworkStatusListener;
import in.ajitesh.chatterbox.interfaces.IChatPresenter;
import in.ajitesh.chatterbox.interfaces.IChatView;
import in.ajitesh.chatterbox.module.ApiModule;
import in.ajitesh.chatterbox.module.ChatDataModule;
import in.ajitesh.chatterbox.module.NetworkModule;
import in.ajitesh.chatterbox.presenter.ChatPresenter;
import in.ajitesh.chatterbox.view.ChatScreenActivity;

/**
 * Created by ajitesh on 11/10/18.
 */

@Singleton
@Component(modules = {ChatDataModule.class, ApiModule.class, NetworkModule.class})
public interface IChatComponent {

    void injectToView(ChatScreenActivity view);

    void injectToPresenter(ChatPresenter chatPresenter);
}
