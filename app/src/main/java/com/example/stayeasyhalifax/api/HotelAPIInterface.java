package com.example.stayeasyhalifax.api;

import com.example.stayeasyhalifax.models.HotelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface HotelAPIInterface {
    // API endpoints
    @GET("/hotel")
    public Call<List<HotelData>> getHotelsLists();
}
