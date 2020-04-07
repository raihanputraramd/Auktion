package com.example.auktion.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.auktion.model.Admin;

import java.util.List;

@Dao
public interface AdminDAO {
    @Query("SELECT * FROM admin where username = :username and password = :password")
    List<Admin> getAllAdmin(String username, String password);

    @Insert
    void insertAdmin(Admin admin);

}
