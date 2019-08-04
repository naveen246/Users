package com.example.users.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("name")
    private Name name;

    @SerializedName("email")
    private String email;

    @SerializedName("dob")
    private Dob dob;

    @SerializedName("gender")
    private String gender;

    @SerializedName("login")
    private Login login;

    public String getName() {
        return name.first + " " + name.last;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return dob.age;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return login.uuid;
    }
}

class Name {

    @SerializedName("title")
    String title;

    @SerializedName("first")
    String first;

    @SerializedName("last")
    String last;
}

class Dob {

    @SerializedName("date")
    String date;

    @SerializedName("age")
    Integer age;
}

class Login {

    @SerializedName("uuid")
    String uuid;
}
