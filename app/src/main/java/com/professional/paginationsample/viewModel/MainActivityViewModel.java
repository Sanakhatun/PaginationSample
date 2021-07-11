package com.professional.paginationsample.viewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.professional.paginationsample.network.ApiClient;
import com.professional.paginationsample.repository.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    public void fetchUsers() {
        Call<User> call = ApiClient.getApiClient().fetchUsers();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i("Response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
