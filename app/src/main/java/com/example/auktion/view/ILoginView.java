package com.example.auktion.View;

public interface ILoginView {
    void onLoginSuccess(String message);

    void onLoginError(String message);

    void gotoRegister();
}
