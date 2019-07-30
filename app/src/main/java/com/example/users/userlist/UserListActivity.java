package com.example.users.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.users.Constants;
import com.example.users.R;
import com.example.users.data.local.model.User;
import com.example.users.userdetail.UserDetailActivity;
import com.example.users.utils.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        List<User> users = new ArrayList<>();
        UserListAdapter adapter = new UserListAdapter(users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                        String userId = ((UserListAdapter) recyclerView.getAdapter()).getUsers().get(position).id;
                        intent.putExtra(Constants.USER_ID, userId);
                        startActivity(intent);
                    }
                }
        );
    }
}
