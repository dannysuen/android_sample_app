package org.railstutorial.sampleapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.railstutorial.sampleapp.api.ApiRoot;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danny on 16-6-2.
 */
public class SampleAppApplication extends Application {

    private Retrofit mRetrofitSingleton;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor())
                .build();

        mRetrofitSingleton = new Retrofit.Builder()
                .baseUrl(ApiRoot.BASE_URL)
                .callFactory(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        initPicasso(client);
    }

    private void initPicasso(OkHttpClient client) {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();
        Picasso.setSingletonInstance(picasso);
    }

    public Retrofit getRetrofit() {
        return mRetrofitSingleton;
    }
}
