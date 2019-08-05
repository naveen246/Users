package com.example.users.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.users.R;
import com.example.users.userdetail.UserDetailActivity;
import com.example.users.utils.Constants;
import com.example.users.utils.ItemClickSupport;

public class UserListActivity extends AppCompatActivity {

    private static final String TAG = UserListActivity.class.getName();
    private UserListViewModel viewModel;
    private UserListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.recyclerview);
        setUpRecyclerView();
        listenToRecyclerViewItemClick();

        setUpViewModel();
    }

    private void setUpRecyclerView() {
        adapter = new UserListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void listenToRecyclerViewItemClick() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                (recyclerView, position, v) -> {
                    Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                    String userId = ((UserListAdapter) recyclerView.getAdapter()).getUsers().get(position).getId();
                    Log.d(TAG, userId);
                    intent.putExtra(Constants.USER_ID, userId);
                    startActivity(intent);
                });
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        viewModel.getUsers().observe(this, users -> adapter.setUsers(users));
    }
}
