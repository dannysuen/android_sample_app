package org.railstutorial.sampleapp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(LoginActivity activity);

    void inject(UsersActivity activity);

    void inject(SampleAppApplication application);

}
