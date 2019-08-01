package com.example.users.data.local.model.response;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("gender")
    public String gender;

    @SerializedName("name")
    public Name name;

    @SerializedName("email")
    public String email;

    @SerializedName("dob")
    public Dob dob;

    @SerializedName("id")
    public Id id;

}
