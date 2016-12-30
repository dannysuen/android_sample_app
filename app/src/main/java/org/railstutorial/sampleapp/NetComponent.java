package org.railstutorial.sampleapp;

import org.railstutorial.sampleapp.auth.LoginActivity;
import org.railstutorial.sampleapp.auth.LoginInteractor;
import org.railstutorial.sampleapp.auth.LoginPresenter;
import org.railstutorial.sampleapp.auth.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, UserModule.class})
public interface NetComponent {

    void inject(LoginActivity activity);

    void inject(UsersActivity activity);

    void inject(SampleAppApplication application);

    void inject(LoginPresenter loginPresenter);

    void inject(UserRepository userRepository);

    void inject(LoginInteractor loginInteractor);
}
