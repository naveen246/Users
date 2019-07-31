package com.example.users.userlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.users.data.UsersRepository;
import com.example.users.data.local.model.User;

import java.util.List;

public class UserListViewModel extends ViewModel {

    private UsersRepository repository;
    private LiveData<List<User>> users;

    LiveData<List<User>> getUsers() {

    }
}
