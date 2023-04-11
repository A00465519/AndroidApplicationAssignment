package com.example.stayeasyhalifax.models;

import androidx.annotation.NonNull;

public class HotelData {
    String hotel_name;
    String price;
    String address;
    double ratings;
    String availability;

    public HotelData(String hotel_name, String price, String address,  double ratings, String availability) {
        this.hotel_name = hotel_name;
        this.price = price;
        this.address = address;
        this.ratings = ratings;
        this.availability = availability;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public String toString() {
        return "HotelData{" +
                "hotel_name='" + hotel_name + '\'' +
                ", price='" + price + '\'' +
                ", address='" + address + '\'' +
                ", availability='" + availability + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}
