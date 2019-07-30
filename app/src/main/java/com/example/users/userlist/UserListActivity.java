package com.example.users.userlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.MutableSelection;
import androidx.recyclerview.selection.Selection;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.users.R;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        contacts = Contact.createContactsList(20);
        UserListAdapter adapter = new UserListAdapter(contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SelectionTracker tracker = new SelectionTracker.Builder<>(
                "user-selection",
                recyclerView,
                
        )
    }
}
