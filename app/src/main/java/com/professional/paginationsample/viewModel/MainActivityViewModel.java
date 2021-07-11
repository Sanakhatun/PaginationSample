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

    public MutableLiveData<ArrayList<User.Data>> fetchUsersList = new MutableLiveData<>();
    private ArrayList<User.Data> userArrayList;

    public void fetchUsers() {
        Call<User> call = ApiClient.getApiClient().fetchUsers();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i("Response", response.toString());
                    User user = response.body();

                    userArrayList = new ArrayList<>();
                    for (int i = 0; i < user.data.size(); i++) {
                        userArrayList.add(user.data.get(i));
                    }

                    fetchUsersList.postValue(user.data);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
