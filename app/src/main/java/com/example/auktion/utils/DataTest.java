package com.example.auktion.utils;

import com.example.auktion.model.Barang;

import java.util.ArrayList;

public class DataTest {

    private static String[] namaBarang = {

            "Televisi",
            "Mobil"
    };
    private static String[] hargaBarang = {

            "Rp.2000203923",
            "Rp2e238232"
    };
    private static String[] deskripsiBarang = {

            "Bagus",
            "Jelek"
    };
    private static String[] tanggalBarang = {

            "50000",
            "25000"
    };

    public static ArrayList<Barang> getListData() {
        ArrayList<Barang> list = new ArrayList<>();
        for (int i = 0; i < namaBarang.length; i++) {
            Barang barang = new Barang(null, null, null,
                    null, 0);
            barang.setNama_barang(namaBarang[i]);
            barang.setHarga_awal(hargaBarang[i]);
//            barang.setDeskripsi_barang(deskripsiBarang[i]);
//            barang.setTgl(tanggalBarang[i]);
            list.add(barang);
        }
        return list;
    }
}
