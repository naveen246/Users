package com.example.users.userdetail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.users.R;
import com.example.users.data.local.model.User;
import com.example.users.utils.Constants;

public class UserDetailActivity extends AppCompatActivity {

    UserDetailViewModel viewModel;
    TextView userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        userDetails = findViewById(R.id.userdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);

        if (savedInstanceState != null) {
            String id = savedInstanceState.getString(Constants.USER_ID);
            viewModel.getUser(id).observe(this, this::setUserDetails);
        }
    }

    private void setUserDetails(User user) {
        String newLine = System.getProperty("line.separator");
        String userData = user.getName() + newLine +
                user.getEmail() + newLine +
                user.getAge() + newLine +
                user.getGender();
        userDetails.setText(userData);
    }
}
