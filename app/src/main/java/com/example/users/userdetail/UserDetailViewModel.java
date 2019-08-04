package com.example.users.userdetail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.users.UsersApp;
import com.example.users.data.UsersRepository;
import com.example.users.data.local.model.User;

class UserDetailViewModel extends AndroidViewModel {

    private UsersRepository repository;

    public UserDetailViewModel(Application app) {
        super(app);

        repository = ((UsersApp) app).getRepository();
    }

    LiveData<User> getUser(String id) {
        return repository.getUser(id);
    }
}
