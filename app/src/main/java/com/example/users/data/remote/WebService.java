package com.example.users.data.remote;

import com.example.users.data.local.model.User;
import com.example.users.data.local.model.response.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    // URL = https://randomuser.me/api/?seed=abc&results={userCount}
    @GET("?seed=abc")
    Call<List<Person>> getUsers(@Query("results") int userCount);
}
