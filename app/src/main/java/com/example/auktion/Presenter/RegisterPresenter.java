package com.example.auktion.Presenter;

import com.example.auktion.Model.User;
import com.example.auktion.View.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter {

    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }


    @Override
    public void onRegister(String username, String password, String namaLengkap, String telepon) {
        User user = new User(username, password, namaLengkap, telepon);

        int isLoginCode = user.isValidData();

        switch (isLoginCode) {
            case 0:
                iRegisterView.onRegisterSuccess("Data Berhasil dimasukan");
                break;
            case 1:
                iRegisterView.onRegisterError("Mohon input data dengan benar!");
            case 2:
                iRegisterView.onRegisterError("Mohon masukan nama anda!");
                break;
            case 3:
                iRegisterView.onRegisterError("Mohon masukan password anda!");
                break;
            case 4:
                iRegisterView.onRegisterError("Mohon masukan nama lengkap anda!");
                break;
            case 5:
                iRegisterView.onRegisterError("Mohon masukan nomor telepon anda!");
                break;
        }


    }
}
