package org.railstutorial.sampleapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    SampleAppApplication mApplication;

    public AppModule(SampleAppApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    SampleAppApplication providesApplication() {
        return mApplication;
    }
}
