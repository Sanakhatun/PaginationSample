package com.professional.paginationsample.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.professional.paginationsample.network.ApiClient;
import com.professional.paginationsample.repository.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    /**
     * Reference:
     * https://developer.android.com/topic/libraries/architecture/livedata
     */

    public MutableLiveData<User> fetchUsers = new MutableLiveData<>();
    private ArrayList<User> userArrayList;

    public void fetchUsers(int pages) {
        Call<User> call = ApiClient.getApiClient().fetchUsers(pages);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i("Response", response.toString());
                    User user = response.body();

                    fetchUsers.postValue(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
