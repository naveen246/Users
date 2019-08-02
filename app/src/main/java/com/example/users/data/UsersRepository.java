package com.example.users.data;

import androidx.lifecycle.LiveData;

import com.example.users.UsersApp;
import com.example.users.data.local.UsersDatabase;
import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.data.remote.ApiClient;
import com.example.users.data.remote.ResponseParser;
import com.example.users.data.remote.response.Result;
import com.example.users.utils.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static UsersRepository INSTANCE;
    private UsersDao usersDao;
    private int userCount = 10;

    private UsersRepository(UsersApp app) {
        usersDao = app.getDatabase().usersDao();
    }

    public static UsersRepository getInstance(UsersApp app) {
        synchronized (UsersRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new UsersRepository(app);
            }
            return INSTANCE;
        }
    }

    // DB calls

    public LiveData<List<User>> getUsers() {
        List<User> users = usersDao.getUsers().getValue();
        // if users is null or empty call remote url and update db
        if (users == null || users.isEmpty() || isTimeToRefreshDb()) {
            callApiAndSaveToDb();
        }
        return usersDao.getUsers();
    }

    public LiveData<User> getUser(int userId) {
        return usersDao.getUser(userId);
    }

    public void saveUsers(List<User> users) {
        // store current time to shared prefs - System.currentTimeMillis()
        AppExecutors.getInstance().diskIO().execute(() -> usersDao.insertUsers(users));
    }



    private boolean isTimeToRefreshDb() {
        return true;
    }

    private void callApiAndSaveToDb() {
        Call<Result> call = ApiClient.webService().getUsers(userCount);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                List<User> users = ResponseParser.parse(response.body());
                saveUsers(users);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }


}
