package com.professional.paginationsample.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int per_page;

    @SerializedName("total")
    private int total;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("data")
    private ArrayList<Data> data;

    class Data {
        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email = "";

        @SerializedName("first_name")
        private String first_name = "";

        @SerializedName("last_name")
        private String last_name = "";

        @SerializedName("avatar")
        private String avatar = "";
    }

}
