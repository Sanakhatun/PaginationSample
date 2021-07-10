package com.professional.paginationsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.professional.paginationsample.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView rv_list;

    private ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_list = findViewById(R.id.rv_list);

        arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        recyclerViewAdapter = new RecyclerViewAdapter(this, arrayList);
        rv_list.setAdapter(recyclerViewAdapter);
        rv_list.setLayoutManager(new LinearLayoutManager(this));

    }
}