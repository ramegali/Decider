package com.nedo.decider;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant implements Parcelable {
    private String name;
    private String phone;
    private String website;
    private double rating;
    private String imageUrl;
    private ArrayList<String> address;
    private double latitude;
    private double longitude;
    private ArrayList<String> categories;

    public Restaurant(String name, String phone, String website,
                      double rating, String imageUrl, ArrayList<String> address,
                      double latitude, double longitude, ArrayList<String> categories) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    public Restaurant(Parcel source) {
        this.name = source.readString();
        this.phone = source.readString();
        this.website = source.readString();
        this.rating = source.readDouble();
        this.imageUrl = source.readString();
        this.address = source.readArrayList(null);
//        this.address = source.createStringArrayList();
        this.latitude = source.readDouble();
        this.longitude = source.readDouble();
        this.categories = source.readArrayList(null);
//        this.categories = source.createStringArrayList();
    }

    public String getName() { return name; }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return  website;
    }

    public double getRating() { return rating; }

    public String getImageUrl(){
        return imageUrl;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.website);
        dest.writeDouble(this.rating);
        dest.writeString(this.imageUrl);
        dest.writeList(this.address);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeList(this.categories);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR
            = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}