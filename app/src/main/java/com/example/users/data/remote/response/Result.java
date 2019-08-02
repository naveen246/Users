package com.example.users.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("results")
    public List<Person> people = null;
}

