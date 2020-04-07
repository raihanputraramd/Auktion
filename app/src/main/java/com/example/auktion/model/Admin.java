package com.example.auktion.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Admin implements IAdmin {

    @PrimaryKey(autoGenerate = true)
    int id_petugas;
    @ColumnInfo(name = "nama_petugas")
    String nama_petugas;
    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "password")
    String password;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // TODO: 4/5/2020 cari tau tentang foreign key di database room
}
