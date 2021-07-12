package com.professional.paginationsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.professional.paginationsample.R;
import com.professional.paginationsample.common.PaginationScrollListener;
import com.professional.paginationsample.repository.model.User;
import com.professional.paginationsample.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView rv_list;

    private ProgressBar progress_circular;

    private ArrayList<User.Data> getUsersList = new ArrayList<>();

    private MainActivityViewModel mainActivityViewModel;

    private static final int PAGE_START = 1;

    private int currentPage = PAGE_START;

    private int pageSize;

    private int totalPages = 0;

    private boolean isLoading;

    private LinearLayoutManager linearLayoutManager;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mainActivityViewModel.fetchUsers.observe(this, user -> {
            totalPages = user.total_pages; /*Total Number of Pages*/
            currentPage = user.page;      /*Current Page*/
            pageSize = user.per_page;  /*Total number of items per page - Threshold*/

            if (currentPage == PAGE_START) {
                getUsersList = user.data;
            } else {
                getUsersList.addAll(user.data);
            }

            Log.i(TAG, "getUsersList: " + getUsersList);

            recyclerViewAdapter = new RecyclerViewAdapter(this, getUsersList);
            rv_list.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();

            if (progress_circular.getVisibility() == View.VISIBLE) {
                progress_circular.setVisibility(View.GONE);
            }
        });

    }

    private void init() {

        rv_list = findViewById(R.id.rv_list);
        progress_circular = findViewById(R.id.progress_circular);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        if (mainActivityViewModel != null) {
            mainActivityViewModel.fetchUsers(PAGE_START);
        }

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_list.setLayoutManager(linearLayoutManager);

        loadMore();

    }

    private void loadMore() {
        rv_list.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage++;
                isLoading = true; /* To indicate next page is loading */
                mainActivityViewModel.fetchUsers(currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLastPage() {
                return currentPage == totalPages ? true : false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }
        });
    }


}