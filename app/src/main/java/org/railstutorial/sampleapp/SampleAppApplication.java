package org.railstutorial.sampleapp;

import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import android.app.Application;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by danny on 16-6-2.
 */
public class SampleAppApplication extends Application {

    public static NetComponent NET_COMPONENT;

    @Inject
    OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        NET_COMPONENT = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        NET_COMPONENT.inject(this);

        initPicasso(mClient);
    }

    private void initPicasso(OkHttpClient client) {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();
        Picasso.setSingletonInstance(picasso);
    }
}
