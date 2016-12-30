package com.lazada.android.mini_mvp.presenter;


import com.lazada.android.mini_mvp.view.MvpView;

public interface MvpPresenter<T extends MvpView> {

    void setView(T view);

    T getView();

}
