package com.example.users.data;

import androidx.lifecycle.LiveData;

import com.example.users.UsersApp;
import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.data.remote.ApiClient;
import com.example.users.data.remote.ResponseParser;
import com.example.users.data.remote.response.Result;
import com.example.users.utils.AppExecutors;
import com.example.users.utils.Constants;
import com.example.users.utils.PreferencesManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static UsersRepository INSTANCE;
    private PreferencesManager preferencesManager;
    private UsersDao usersDao;
    private int userCount = 10;

    private UsersRepository(UsersApp app) {
        preferencesManager = app.getPreferencesManager();
        usersDao = app.getDatabase().usersDao();
    }

    public static synchronized UsersRepository getInstance(UsersApp app) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(app);
        }
        return INSTANCE;
    }

    // DB calls

    public LiveData<List<User>> getUsers() {
        List<User> users = usersDao.getUsers().getValue();
        if (users == null || users.isEmpty() || isTimeToRefreshDb()) {
            callApiAndSaveResultToDb();
        }
        return usersDao.getUsers();
    }

    public LiveData<User> getUser(String userId) {
        return usersDao.getUser(userId);
    }

    private void saveUsers(List<User> users) {
        saveCurrentTimeAsDbUpdateTime();
        AppExecutors.getInstance().diskIO().execute(() -> usersDao.insertUsers(users));
    }


    private boolean isTimeToRefreshDb() {
        long dbRefreshInterval = TimeUnit.MINUTES.toMillis(Constants.DB_REFRESH_INTERVAL_MIN);
        return (System.currentTimeMillis() - getLastDbUpdateTime()) > dbRefreshInterval;
    }

    private void callApiAndSaveResultToDb() {
        Call<Result> call = ApiClient.webService().getUsers(userCount);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() == null) return;
                List<User> users = ResponseParser.parse(response.body());
                saveUsers(users);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void saveCurrentTimeAsDbUpdateTime() {
        preferencesManager.setLongValue(Constants.LAST_DB_UPDATE_TIME, System.currentTimeMillis());
    }

    private long getLastDbUpdateTime() {
        return preferencesManager.getLongValue(Constants.LAST_DB_UPDATE_TIME);
    }
}
