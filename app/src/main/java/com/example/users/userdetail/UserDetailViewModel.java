package com.example.users.userdetail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.users.UsersApp;
import com.example.users.data.UsersRepository;
import com.example.users.data.local.model.User;

public class UserDetailViewModel extends AndroidViewModel {

    private final MutableLiveData<User> observableUser;
    private UsersRepository repository;

    public UserDetailViewModel(Application app) {
        super(app);

        observableUser = new MediatorLiveData<>();

        repository = ((UsersApp) app).getRepository();
    }

    LiveData<User> getUser(String id) {
        return repository.getUser(id);
    }
}
