package org.railstutorial.sampleapp.auth;

import com.lazada.android.mini_mvp.repository.Repository;

import org.railstutorial.sampleapp.SampleAppApplication;
import org.railstutorial.sampleapp.api.SessionApiService;
import org.railstutorial.sampleapp.api.UserApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository implements Repository {

    interface Callback {
        void onUserSuccess(User user);
        void onUserFailure(String errorMessage);
    }

    @Inject
    SessionApiService mService;

    public UserRepository() {
        SampleAppApplication.netComponent().inject(this);
    }

    public void loadUser(String username, String password, final Callback callback) {
        Call<Envelop<User>> call = mService.login(username, password);
        call.enqueue(new retrofit2.Callback<Envelop<User>>() {
            @Override
            public void onResponse(Call<Envelop<User>> call, Response<Envelop<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onUserSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<Envelop<User>> call, Throwable t) {
                callback.onUserFailure(t.toString());
            }
        });
    }

}
