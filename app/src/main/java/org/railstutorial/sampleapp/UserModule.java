package org.railstutorial.sampleapp;

import org.railstutorial.sampleapp.api.SessionApiService;
import org.railstutorial.sampleapp.auth.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserModule {

    @Singleton
    @Provides
    public UserRepository provideUserRepository() {
        return new UserRepository();
    }

    @Singleton
    @Provides
    public SessionApiService provideSessionApiService(Retrofit retrofit) {
        return retrofit.create(SessionApiService.class);
    }



}
