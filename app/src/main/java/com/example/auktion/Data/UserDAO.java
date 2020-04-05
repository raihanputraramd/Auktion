package com.example.auktion.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.auktion.Model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user where username= :username and password= :password")
    List<User> getAll(String username, String password);

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}