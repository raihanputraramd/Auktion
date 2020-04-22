package com.example.auktion.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.auktion.model.User;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user where username= :username and password= :password")
    User userlogin(String username, String password);

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE id_user = :id LIMIT 1")
    User userProfile(int id);

}
