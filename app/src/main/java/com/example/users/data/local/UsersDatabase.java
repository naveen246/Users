package com.example.users.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.users.data.local.dao.UserDao;
import com.example.users.data.local.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "users-db";
    private static UsersDatabase INSTANCE;

    public static synchronized UsersDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UsersDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
