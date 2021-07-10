package com.professional.paginationsample.network;

import com.professional.paginationsample.repository.remote.api.ApiInterface;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Retrofit retrofit = null;

    static ApiInterface getApiClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
