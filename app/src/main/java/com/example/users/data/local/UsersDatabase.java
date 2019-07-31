package com.example.users.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.utils.AppExecutors;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    private static UsersDatabase INSTANCE;
    public static final String DATABASE_NAME = "users-db";
    private static AppExecutors executors;

    public abstract UsersDao usersDao();

    public static UsersDatabase getInstance(final Context context, final AppExecutors executors) {
        if (UsersDatabase.executors == null) {
            UsersDatabase.executors = executors;
        }

        synchronized (UsersDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UsersDatabase.class, DATABASE_NAME)
                        .addCallback(roomDatabaseCallback)
                        .build();
            }
            return INSTANCE;
        }

    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateDB();
        }
    };

    private static void populateDB() {
        executors.diskIO().execute(() -> {
            // call network and populate db
        });
    }
}
