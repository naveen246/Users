package com.example.users.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.users.data.local.model.User;

import java.util.List;

public interface UsersDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> getUser(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);
}
