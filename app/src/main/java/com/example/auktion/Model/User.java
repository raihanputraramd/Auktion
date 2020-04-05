package com.example.auktion.Model;

import android.text.TextUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User implements IUser {

    @PrimaryKey(autoGenerate = true)
    int id_user;
    @ColumnInfo(name = "nama_lengkap")
    String nama_lengkap;
    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "telp")
    String telp;

    public User(String nama_lengkap, String username, String password, String telp) {
        this.nama_lengkap = nama_lengkap;
        this.username = username;
        this.password = password;
        this.telp = telp;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int isValidData() {
        if (TextUtils.isEmpty(getUsername()) && TextUtils.isEmpty(getPassword())
                && TextUtils.isEmpty(getNama_lengkap()) && TextUtils.isEmpty(getTelp())) {
            return 1;
        } else if (TextUtils.isEmpty(getUsername())) {
            return 2;
        } else if (TextUtils.isEmpty(getPassword())) {
            return 3;
        } else if (TextUtils.isEmpty(getNama_lengkap())) {
            return 4;
        } else if (TextUtils.isEmpty(getTelp())) {
            return 5;
        } else {
            return 0;
        }
    }
}
