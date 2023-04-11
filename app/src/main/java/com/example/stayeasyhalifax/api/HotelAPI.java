package com.example.stayeasyhalifax.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelAPI {
    public static HotelAPIInterface getClient() {

        // change your base URL
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://localhost:8088/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(HotelAPIInterface.class); // return the APIInterface object
    }
}
