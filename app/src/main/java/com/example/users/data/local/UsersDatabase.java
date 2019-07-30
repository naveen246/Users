package com.example.users.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    abstract UsersDao usersDao();
}
