package com.example.auktion.presenter;

import com.example.auktion.model.User;
import com.example.auktion.view.IUserRegisterView;

public class UserUserRegisterPresenter implements IUserRegisterPresenter {

    IUserRegisterView iUserRegisterView;

    public UserUserRegisterPresenter(IUserRegisterView iUserRegisterView) {
        this.iUserRegisterView = iUserRegisterView;
    }


    @Override
    public void onRegister(String username, String password, String namaLengkap, String telepon) {
        User user = new User(username, password, namaLengkap, telepon);

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
