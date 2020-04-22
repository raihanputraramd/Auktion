package com.example.auktion.activityUser.presenter;

import com.example.auktion.model.User;
import com.example.auktion.activityUser.view.IUserRegisterView;

public class UserRegisterPresenter implements IUserRegisterPresenter {

    private IUserRegisterView iUserRegisterView;

    public UserRegisterPresenter(IUserRegisterView iUserRegisterView) {
        this.iUserRegisterView = iUserRegisterView;
    }


    @Override
    public void onRegister(User user) {

        int isLoginCode = user.isValidData();

        switch (isLoginCode) {
            case 0:
                iUserRegisterView.onRegisterSuccess("Data Berhasil dimasukan");
                break;
            case 1:
                iUserRegisterView.onRegisterError("Mohon input data dengan benar!");
            case 2:
                iUserRegisterView.onRegisterError("Mohon masukan nama anda!");
                break;
            case 3:
                iUserRegisterView.onRegisterError("Mohon masukan password anda!");
                break;
            case 4:
                iUserRegisterView.onRegisterError("Mohon masukan nama lengkap anda!");
                break;
            case 5:
                iUserRegisterView.onRegisterError("Mohon masukan nomor telepon anda!");
                break;
        }


    }
}
