package com.example.users.data.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String email;
    private int age;
    private String gender;

    public User(String id, String name, String email, int age, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + "\n\n" + getName() + "\n\n" + getEmail() + "\n\n" + getAge() + "\n\n" + getGender() + "\n";
    }
}
