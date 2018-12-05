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
//                String jsonData = response.body().string();
                if (response.isSuccessful()) {
//                    Log.v(TAG, jsonData);
                    restaurants = yelpService.processResults(response);
                    Log.v(TAG, "hi from restaurantsactivity: " + restaurants.get(0).getName());
//                    for (int i = 0; i < restaurants.size(); i++) {
//                        Log.v(TAG, "NAME: " + restaurants.get(i).getName());
//                        for (String categories : restaurants.get(i).getCategories()
//                             ) {
//                            Log.v(TAG, "CATEGORIES: " + categories);
//                        }
//                    }

//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelableArrayList("restaurants", restaurants);
//                    intent.putExtras(bundle);
//                    startActivity(intent);


                    RestaurantsActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            Intent intent = new Intent(RestaurantsActivity.this, MainActivity.class);
                            intent.putParcelableArrayListExtra("restaurants", restaurants);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

}
