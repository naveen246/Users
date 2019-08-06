package com.example.users.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.users.data.local.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> getUser(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);
}
