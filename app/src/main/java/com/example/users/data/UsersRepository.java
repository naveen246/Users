package com.example.users.data;

import androidx.lifecycle.LiveData;

import com.example.users.data.local.UsersDatabase;
import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.utils.AppExecutors;

import java.util.List;

public class UsersRepository {
    private UsersDao usersDao;
    private static UsersRepository INSTANCE;

    private UsersRepository(UsersDatabase database) {
        usersDao = database.usersDao();
    }

    public static UsersRepository getInstance(UsersDatabase database) {
        synchronized (UsersRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new UsersRepository(database);
            }
            return INSTANCE;
        }
    }

    LiveData<List<User>> getUsers() {
        LiveData<List<User>> users = usersDao.getUsers();
        if (users.)
        return usersDao.getUsers();
    }

    LiveData<User> getUser(int userId) {
        return usersDao.getUser(userId);
    }

    void insertUsers(User... users) {
        AppExecutors.getInstance().diskIO().execute(() -> usersDao.insertUsers(users));
    }
}
