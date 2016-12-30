package org.railstutorial.sampleapp.auth;


import com.lazada.android.mini_mvp.model.Interactor;

import org.railstutorial.sampleapp.SampleAppApplication;
import org.railstutorial.sampleapp.model.User;

import javax.inject.Inject;


public class LoginInteractor implements Interactor {

    interface Callback {
        void onLoginSuccess(User user);

        void onLoginFailure(String error);
    }

    @Inject
    UserRepository mRepository;

    public LoginInteractor() {
        SampleAppApplication.netComponent().inject(this);
    }

    public void performLogin(String username, String password, final Callback callback) {
        mRepository.loadUser(username, password, new UserRepository.Callback() {
            @Override
            public void onUserSuccess(User user) {
                callback.onLoginSuccess(user);
            }

            @Override
            public void onUserFailure(String errorMessage) {
                callback.onLoginFailure(errorMessage);
            }
        });
    }

}
