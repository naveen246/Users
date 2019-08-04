package com.example.users.userdetail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.users.R;
import com.example.users.data.local.model.User;
import com.example.users.utils.Constants;

public class UserDetailActivity extends AppCompatActivity {

    private static final String TAG = UserDetailActivity.class.getName();
    private UserDetailViewModel viewModel;
    private TextView userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        userDetails = findViewById(R.id.userdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);

        String id = getIntent().getStringExtra(Constants.USER_ID);
        viewModel.getUser(id).observe(this, this::setUserDetails);
    }

    private void setUserDetails(User user) {
        userDetails.setText(user.toString());
    }
}
