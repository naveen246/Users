package com.example.users.userdetail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.users.R;
import com.example.users.data.local.model.User;
import com.example.users.utils.Constants;

public class UserDetailActivity extends AppCompatActivity {

    private UserDetailViewModel viewModel;
    private TextView userDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        userDetailsTextView = findViewById(R.id.userdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(Constants.USER_ID);
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);
        viewModel.getUser(id).observe(this, this::setUserDetails);
    }

    private void setUserDetails(User user) {
        userDetailsTextView.setText(user.toString());
    }
}
