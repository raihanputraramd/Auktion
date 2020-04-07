package com.example.auktion.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.auktion.data.AdminDAO;
import com.example.auktion.data.UserDAO;
import com.example.auktion.model.Admin;
import com.example.auktion.model.User;

@Database(entities = {User.class, Admin.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "dbLelang.db";


    public abstract UserDAO getuserDAO();

    public abstract AdminDAO getAdminDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
