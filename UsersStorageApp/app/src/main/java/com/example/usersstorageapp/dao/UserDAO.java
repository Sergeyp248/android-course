package com.example.usersstorageapp.dao;

import com.example.usersstorageapp.entities.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query("SELECT * FROM user WHERE id = :userId")
    User getById(long userId);

    @Query("SELECT * FROM user WHERE first_name LIKE :value or last_name LIKE :value")
    User getByFirstNameOrLastname(String value);

    @Insert
    void insertUser(User user);
}
