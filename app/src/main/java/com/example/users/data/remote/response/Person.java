package com.example.users.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("name")
    Name name;

    @SerializedName("email")
    String email;

    @SerializedName("dob")
    Dob dob;

    @SerializedName("gender")
    String gender;

    @SerializedName("login")
    Login login;

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
