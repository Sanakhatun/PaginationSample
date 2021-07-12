package com.professional.paginationsample.repository.remote.api;

import com.professional.paginationsample.repository.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/users")
    Call<User> fetchUsers(@Query("page") int pages);
}
