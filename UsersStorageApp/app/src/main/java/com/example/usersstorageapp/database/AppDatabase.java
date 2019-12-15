package com.example.usersstorageapp.database;

import com.example.usersstorageapp.dao.UserDAO;
import com.example.usersstorageapp.entities.User;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}
