package com.example.auktion.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.auktion.data.AdminDAO;
import com.example.auktion.data.BarangDAO;
import com.example.auktion.data.UserDAO;
import com.example.auktion.model.Admin;
import com.example.auktion.model.Barang;
import com.example.auktion.model.User;

@Database(entities = {User.class, Admin.class, Barang.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public abstract UserDAO getuserDAO();

    public abstract AdminDAO getAdminDao();

    public abstract BarangDAO getBarangDAO();
}
