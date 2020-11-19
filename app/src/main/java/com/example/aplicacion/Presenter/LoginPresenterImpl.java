package com.example.aplicacion.Presenter;

import android.content.SharedPreferences;

import com.example.aplicacion.Model.LoginInteractor;
import com.example.aplicacion.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;


    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String username, String password) {

        if (loginView!=null){
            loginView.showProgress();
        }
        loginInteractor.login(username,password, this);
    }

    @Override
    public void onDestroy() {

        loginView=null;
    }

    private LoginInteractor loginInteractor;

    @Override
    public void onUsernameError() {

        if (loginView!=null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {

        if (loginView!=null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {

        if (loginView!=null){
            loginView.navigateToHome();
            loginView.hideProgress();
        }
    }


}
