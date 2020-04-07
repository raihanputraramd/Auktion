package com.example.auktion.presenter;

import com.example.auktion.model.Admin;
import com.example.auktion.view.IUserRegisterView;

public class AdminRegisterPresenter implements IAdminRegisterPresenter {

    private IUserRegisterView iUserRegisterView;

    public AdminRegisterPresenter(IUserRegisterView iUserRegisterView) {
        this.iUserRegisterView = iUserRegisterView;
    }

    @Override
    public void onRegister(String nama_petugas, String username, String password) {

        Admin admin = new Admin(nama_petugas, username, password);

        int loginCode = admin.isValidData();

        if (loginCode == 0) {
            iUserRegisterView.onRegisterSuccess("Register berhasil sebagai Admin");
        } else {
            iUserRegisterView.onRegisterError("Mohon Masukan Data Dengan Lengkap !");
        }
    }
}
