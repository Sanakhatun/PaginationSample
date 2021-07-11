package com.professional.paginationsample.network;

import com.professional.paginationsample.repository.remote.api.ApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static ApiInterface getApiClient() {

        /**
         * Interceptors are a powerful mechanism that can monitor, rewrite & retry calls
         */

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /**
         * Types of Interceptors:
         * 1. Application Interceptors -> addInterceptor()
         * 2. Network Interceptors     -> addNetworkInterceptor()
         */

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
