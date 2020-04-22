package com.example.auktion.presenter;

import com.example.auktion.model.Barang;
import com.example.auktion.view.IAddBarangView;

public class AddBarangPresenter implements IAddBarangPresenter {

    private IAddBarangView iAddBarangView;

    public AddBarangPresenter(IAddBarangView iAddBarangView) {
        this.iAddBarangView = iAddBarangView;
    }

    @Override
    public void addBarang(Barang barang) {

        int verifyCode = barang.isDataComplete();

        if (verifyCode == 1) {
            iAddBarangView.missingItem("Mohon masukan data dengan lengkap");
        } else if (verifyCode == 2) {
            iAddBarangView.missingItem("Mohon masukan nama barang");
        } else if (verifyCode == 3) {
            iAddBarangView.missingItem("Mohon masukan harga Barang");
        } else if (verifyCode == 4) {
            iAddBarangView.missingItem("Mohon masukan deskripsi barang");
        } else {
            iAddBarangView.confirmAdd("Data Berhasil di input");

        }
    }
}
