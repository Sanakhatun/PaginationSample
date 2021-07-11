package com.professional.paginationsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.professional.paginationsample.R;
import com.professional.paginationsample.repository.model.User;
import com.professional.paginationsample.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView rv_list;

    private ArrayList<User.Data> getUsersList;

    private MainActivityViewModel mainActivityViewModel;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mainActivityViewModel.fetchUsersList.observe(this, data -> {
            getUsersList = data;

            Log.i(TAG, "getUsersList: " + getUsersList);

            recyclerViewAdapter = new RecyclerViewAdapter(this, getUsersList);
            rv_list.setAdapter(recyclerViewAdapter);
            rv_list.setLayoutManager(new LinearLayoutManager(this));
        });

    }

    private void init() {

        rv_list = findViewById(R.id.rv_list);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        if (mainActivityViewModel != null) {
            mainActivityViewModel.fetchUsers();
        }

    }
}