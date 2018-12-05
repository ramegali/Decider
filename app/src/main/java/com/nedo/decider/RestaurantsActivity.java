package com.nedo.decider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getRestaurants(location);
    }

    private void getRestaurants(String location) {

        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    restaurants = yelpService.processResults(response);
//                    for (int i = 0; i < restaurants.size(); i++) {
//                        Log.v(TAG, "NAME: " + restaurants.get(i).getName());
//                        for (String categories : restaurants.get(i).getCategories()
//                             ) {
//                            Log.v(TAG, "CATEGORIES: " + categories);
//                        }
//                    }
                    RestaurantsActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.putParcelableArrayListExtra("restaurants", restaurants);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                }
            }
        });
    }

}
