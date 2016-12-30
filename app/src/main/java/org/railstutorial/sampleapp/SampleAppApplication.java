package org.railstutorial.sampleapp;

import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import android.app.Application;

import org.railstutorial.sampleapp.api.ApiComponent;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by danny on 16-6-2.
 */
public class SampleAppApplication extends Application {

    private static NetComponent NET_COMPONENT;

    public static ApiComponent API_COMPONENT;

    private static SampleAppApplication sInstance;

    @Inject
    OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        Stetho.initializeWithDefaults(this);

        NET_COMPONENT = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        NET_COMPONENT.inject(this);


        SampleAppApplication.NET_COMPONENT.inject(this);


        initPicasso(mClient);
    }

    private void initPicasso(OkHttpClient client) {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();
        Picasso.setSingletonInstance(picasso);
    }

    public static SampleAppApplication getApplication() {
        return sInstance;
    }

    public static NetComponent netComponent() {
        return NET_COMPONENT;
    }
}
