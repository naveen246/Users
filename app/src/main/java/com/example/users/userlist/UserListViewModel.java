package com.example.users.userlist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.users.UsersApp;
import com.example.users.data.UsersRepository;
import com.example.users.data.local.model.User;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<User>> observableUsers;
    private UsersRepository usersRepository;
    private LiveData<List<User>> users;

    public UserListViewModel(Application application) {
        super(application);

        observableUsers = new MediatorLiveData<>();
        observableUsers.setValue(null);

        usersRepository = ((UsersApp) application).getRepository();
        LiveData<List<User>> users = usersRepository.getUsers();

        // observe the changes of the products from the database and forward them
        observableUsers.addSource(users, observableUsers::setValue);


    }

    LiveData<List<User>> getUsers() {
        return observableUsers;
    }
}
