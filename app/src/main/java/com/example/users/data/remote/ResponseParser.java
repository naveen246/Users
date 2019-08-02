package com.example.users.data.remote;

import android.util.Log;

import com.example.users.data.local.model.User;
import com.example.users.data.remote.response.Person;
import com.example.users.data.remote.response.Result;

import java.util.ArrayList;
import java.util.List;

public class ResponseParser {
    public static List<User> parse(Result result) {
        List<Person> people = result.people;
        List<User> users = new ArrayList<>();
        for (Person person : people) {
            User user = new User(person.getId(), person.getName(), person.getEmail(), person.getAge(), person.getGender());
            users.add(user);
            Log.d("User - ", user.toString());
        }
        return users;
    }
}
