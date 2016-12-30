package org.railstutorial.sampleapp.auth;

import com.lazada.android.mini_mvp.view.MvpView;

import org.railstutorial.sampleapp.model.User;


public interface LoginView extends MvpView {

    void startProgress();

    void showLoginSuccess(User user);

    void showLoginFailure(String failureMessage);

    void moveToNext(User loggedInUser);

}
