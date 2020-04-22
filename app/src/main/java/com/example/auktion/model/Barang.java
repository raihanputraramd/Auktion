package com.example.auktion.model;

import android.text.TextUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "barang")
public class Barang implements IBarang, Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_barang;

    @ColumnInfo(name = "nama_barang")
    private String nama_barang;
    @ColumnInfo(name = "harga_awal")
    private String harga_awal;
    @ColumnInfo(name = "deskripsi_barang")
    private String deskripsi_barang;
    @ColumnInfo(name = "tgl")
    private String tgl;
    @ColumnInfo(name = "status")
    private int status;


    public Barang(String nama_barang, String harga_awal, String deskripsi_barang, String tgl, int status) {
        this.nama_barang = nama_barang;
        this.harga_awal = harga_awal;
        this.deskripsi_barang = deskripsi_barang;
        this.tgl = tgl;
        this.status = status;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public void setHarga_awal(String harga_awal) {
        this.harga_awal = harga_awal;
    }

    public String getDeskripsi_barang() {
        return deskripsi_barang;
    }

    public void setDeskripsi_barang(String deskripsi_barang) {
        this.deskripsi_barang = deskripsi_barang;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int isDataComplete() {
        if (TextUtils.isEmpty(getNama_barang()) && TextUtils.isEmpty(getHarga_awal())
                && TextUtils.isEmpty(getDeskripsi_barang())) {
            return 1;
        } else if (TextUtils.isEmpty(getNama_barang())) {
            return 2;
        } else if (TextUtils.isEmpty(getHarga_awal())) {
            return 3;
        } else if (TextUtils.isEmpty(getDeskripsi_barang())) {
            return 4;
        } else {
            return 0;
        }
    }
}
