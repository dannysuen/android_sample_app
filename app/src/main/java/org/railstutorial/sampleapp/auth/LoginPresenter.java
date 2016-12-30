package org.railstutorial.sampleapp.auth;

import com.lazada.android.mini_mvp.presenter.MvpPresenter;

import org.railstutorial.sampleapp.model.User;


public class LoginPresenter implements MvpPresenter<LoginView>, LoginInteractor.Callback {

    private LoginView mLoginView;

    private LoginInteractor mLoginInteractor;


    public LoginPresenter(LoginView loginView, LoginInteractor interactor) {
        mLoginView = loginView;
        mLoginInteractor = interactor;
    }

    @Override
    public LoginView getView() {
        return mLoginView;
    }

    @Override
    public void setView(LoginView view) {
        this.mLoginView = view;
    }

    public void login(String username, String password) {
        mLoginInteractor.performLogin(username, password, this);
    }

    @Override
    public void onLoginSuccess(User user) {
        getView().showLoginSuccess(user);
    }

    @Override
    public void onLoginFailure(String error) {
        getView().showLoginFailure(error);
    }
}
