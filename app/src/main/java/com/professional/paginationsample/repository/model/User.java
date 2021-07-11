package com.professional.paginationsample.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("page")
    public int page;

    @SerializedName("per_page")
    public int per_page;

    @SerializedName("total")
    public int total;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;

        @SerializedName("email")
        public String email = "";

        @SerializedName("first_name")
        public String first_name = "";

        @SerializedName("last_name")
        public String last_name = "";

        @SerializedName("avatar")
        public String avatar = "";
    }

}
