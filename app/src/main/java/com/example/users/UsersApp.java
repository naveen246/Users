package com.example.users;

import android.app.Application;

import com.example.users.data.UsersRepository;
import com.example.users.data.local.UsersDatabase;
import com.example.users.utils.AppExecutors;

public class UsersApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UsersDatabase getDatabase() {
        return UsersDatabase.getInstance(this, AppExecutors.getInstance());
    }

    public UsersRepository getRepository() {
        return UsersRepository.getInstance(getDatabase());
    }

}
