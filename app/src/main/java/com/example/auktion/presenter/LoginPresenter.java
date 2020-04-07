package com.example.auktion.presenter;

import com.example.auktion.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String password) {
//        User user = new User(id,null,username, password,null);
//
//        int isLoginCode = user.isValidData();
//
//        if (isLoginCode == 1) {
//            loginView.onLoginError("Error");
//        } else {
//            loginView.onLoginSuccess("Success");
//        }
    }
}
