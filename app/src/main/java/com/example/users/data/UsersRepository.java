package com.example.users.data;

import androidx.lifecycle.LiveData;

import com.example.users.data.local.UsersDatabase;
import com.example.users.data.local.dao.UsersDao;
import com.example.users.data.local.model.User;
import com.example.users.data.local.model.response.Person;
import com.example.users.data.remote.ApiClient;
import com.example.users.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private UsersDao usersDao;
    private static UsersRepository INSTANCE;
    private int userCount = 10;

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

    public LiveData<List<User>> getUsers() {
        List<User> users = usersDao.getUsers().getValue();
        // if users is null or empty call remote url and update db
        if (users == null || users.size() == 0 || isTimeToRefreshDb()) {
            getUsersFromApi();
        }
        return usersDao.getUsers();
    }

    private void getUsersFromApi() {
        Call<List<Person>> call = ApiClient.webService().getUsers(userCount);
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> people = response.body();
                List<User> users = peopleToUsers(people);
                saveToDb(users);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

            }
        });
    }

    private User personToUser(Person person) {
        String id = person.id.value;
        String name = person.name.first + " " + person.name.last;
        String email = person.email;
        int age = person.dob.age;
        String gender = person.gender;
        return new User(id, name, email, age, gender);
    }

    private List<User> peopleToUsers(List<Person> people) {
        List<User> users = new ArrayList<>();
        for (Person person : people) {
            users.add(personToUser(person));
        }
        return users;
    }

    public LiveData<User> getUser(int userId) {
        return usersDao.getUser(userId);
    }

    public void saveToDb(List<User> users) {
        // store current time to shared prefs
        AppExecutors.getInstance().diskIO().execute(() -> usersDao.insertUsers(users));
    }
}
