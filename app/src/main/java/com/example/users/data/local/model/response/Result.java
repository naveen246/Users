package com.example.users.data.local.model.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("results")
    public List<Person> users = null;
}

