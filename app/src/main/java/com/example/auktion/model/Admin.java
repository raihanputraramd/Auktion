package com.example.auktion.model;

import android.text.TextUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin")
public class Admin implements IAdmin {

    @PrimaryKey(autoGenerate = true)
    int id_petugas;
    @ColumnInfo(name = "nama_petugas")
    String nama_petugas;
    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "password")
    String password;

    public Admin(String nama_petugas, String username, String password) {
        this.nama_petugas = nama_petugas;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int isValidData() {
        if (TextUtils.isEmpty(getUsername()) && TextUtils.isEmpty(getPassword())
                && TextUtils.isEmpty(getNama_petugas())) {
            return 1;
        } else if (TextUtils.isEmpty(getUsername())) {
            return 2;
        } else if (TextUtils.isEmpty(getPassword())) {
            return 3;
        } else if (TextUtils.isEmpty(getNama_petugas())) {
            return 4;
        } else {
            return 0;
        }
    }

    public int getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(int id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }
}
