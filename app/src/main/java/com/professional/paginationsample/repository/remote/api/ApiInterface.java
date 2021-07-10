package com.professional.paginationsample.repository.remote.api;

import com.professional.paginationsample.repository.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/users?page=")
    Call<User> fetchUsers();
}
