package com.nedo.decider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class RestaurantDisplay extends AppCompatActivity {
    public static final String TAG = RestaurantDisplay.class.getSimpleName();
    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        restaurants = intent.getParcelableArrayListExtra("restaurants");
        int index = intent.getIntExtra("index", 0);

        TextView name = findViewById(R.id.restaurantName);
        TextView phone = findViewById(R.id.phoneNumberText);
        TextView address = findViewById(R.id.addressText);
        TextView rating = findViewById(R.id.ratingText);
        TextView categories = findViewById(R.id.categoryText);
        Button startOverBtn = findViewById(R.id.startOverButton);

        Restaurant restaurant = restaurants.get(index);
        Log.v(TAG, "restaurant: " + restaurant.getName());


        name.setText(restaurant.getName());
        phone.setText(restaurant.getPhone());

        String address_str = new String();
        for (String e : restaurant.getAddress()) {
            address_str += e + "\n";
        }
        address.setText(address_str);
        rating.setText(String.valueOf(restaurant.getRating()));
        String category_str = new String();
        for (String e : restaurant.getCategories()) {
            category_str += e + " ";
        }
        categories.setText(category_str);

        startOverBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantDisplay.this, MainActivity.class);
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                String str = tv.getText().toString();
                str = str.replaceAll("\\s+","+");
                str = str.replaceAll("[,]","");
                str = str.substring(0, str.length() - 1);
                Log.v(TAG, "address: " + str);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + str));
                startActivity(intent);

            }
        });
    }
}
