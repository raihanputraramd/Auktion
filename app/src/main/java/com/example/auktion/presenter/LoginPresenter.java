package com.example.auktion.presenter;

import com.example.auktion.model.Admin;
import com.example.auktion.model.User;
import com.example.auktion.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String password) {
        User user = new User(null, username, password, null);
        Admin admin = new Admin(null, username, password);

        validOrNotUser(user);
        validOrNotAdmin(admin);

    }

    private void validOrNotUser(User user) {
        int isLoginCode = user.isValidData();

        if (isLoginCode == 1) {
            loginView.onLoginError("Mohon input data dengan benar !");
        } else if (isLoginCode == 2) {
            loginView.onLoginError("Mohon isi kolom username");
        } else if (isLoginCode == 3) {
            loginView.onLoginError("Mohon isi kolom password");
        } else {

        }
    }

    private void validOrNotAdmin(Admin admin) {
        int isLoginCode = admin.isValidData();
        if (isLoginCode == 1) {
            loginView.onLoginError("Mohon input data dengan benar !");
        } else if (isLoginCode == 2) {
            loginView.onLoginError("Mohon isi kolom username");
        } else if (isLoginCode == 3) {
            loginView.onLoginError("Mohon isi kolom password");
        } else {
        }

    }
}
