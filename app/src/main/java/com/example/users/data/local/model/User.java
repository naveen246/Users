package com.example.users.data.local.model;

import androidx.room.PrimaryKey;

public class User {

    @PrimaryKey public String id;
    public String firstName;
    public String lastname;
    public String email;
    public int age;
    public String gender;
}
