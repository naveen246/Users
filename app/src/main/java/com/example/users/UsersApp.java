package com.example.users;

import android.app.Application;

import com.example.users.data.UsersRepository;
import com.example.users.data.local.UsersDatabase;
import com.example.users.utils.PreferencesManager;

public class UsersApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UsersDatabase getDatabase() {
        return UsersDatabase.getInstance(this);
    }

    public UsersRepository getRepository() {
        return UsersRepository.getInstance(this);
    }

    public PreferencesManager getPreferencesManager() {
        return PreferencesManager.getInstance(this);
    }
}
