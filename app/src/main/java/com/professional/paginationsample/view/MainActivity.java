package com.professional.paginationsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

                recyclerViewAdapter = new RecyclerViewAdapter(this, getUsersList);
                rv_list.setAdapter(recyclerViewAdapter);

            } else {
                getUsersList.remove(getUsersList.size() - 1);
                recyclerViewAdapter.notifyItemRemoved(getUsersList.size());
                getUsersList.addAll(user.data);

                isLoading = false;
            }

            recyclerViewAdapter.notifyDataSetChanged();
            Log.i(TAG, "getUsersList: " + getUsersList);

            if (progress_circular.getVisibility() == View.VISIBLE) {
                progress_circular.setVisibility(View.GONE);
            }
        });

        mainActivityViewModel.failure.observe(this, errorMsg -> {

            if (currentPage == PAGE_START) {
                progress_circular.setVisibility(View.GONE);
                Toast.makeText(this, "Oops, Network Unavailable", Toast.LENGTH_SHORT).show();
            }

            if (getUsersList != null && getUsersList.size() > 0) {
                getUsersList.remove(getUsersList.size() - 1);
                recyclerViewAdapter.notifyItemRemoved(getUsersList.size());
                currentPage--;
                isLoading = false;
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

                User.Data data = new User.Data();
                data.setFirstName("progress");
                getUsersList.add(getUsersList.size(), data);
                recyclerViewAdapter.notifyItemInserted(getUsersList.size());
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