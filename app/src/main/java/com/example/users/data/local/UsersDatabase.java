package com.example.users.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.utils.AppExecutors;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "users-db";
    private static UsersDatabase INSTANCE;
    private static AppExecutors executors;

    public static UsersDatabase getInstance(final Context context, final AppExecutors executors) {
        if (UsersDatabase.executors == null) {
            UsersDatabase.executors = executors;
        }

        synchronized (UsersDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UsersDatabase.class, DATABASE_NAME)
                        .build();
            }
            return INSTANCE;
        }

    }

    public abstract UsersDao usersDao();
}
