package com.nedo.decider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantListDisplay extends AppCompatActivity {

    public ArrayList<Restaurant> restaurants = new ArrayList<>();
    public ArrayList<TextView> textViews = new ArrayList<>();

    static final int NUM_MAX_RESTAURANTS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);

        Intent intent = getIntent();
        restaurants = intent.getParcelableArrayListExtra("restaurants");
        populateList(restaurants);
    }

    // Get an array of TextViews where each view contains the name of a restaurant
    private void populateList(final ArrayList<Restaurant> restaurants) {
        TextView textView;

        for (int i = 0; i < restaurants.size(); i++) {
            String name = "textView" + (i + 1);
            int id = getResources().getIdentifier(name, "id", getPackageName());
            if (id != 0) {
                textView = findViewById(id);
                textView.setText(restaurants.get(i).getName());
                textView.setVisibility(View.VISIBLE);

                // When we click a text view, we pass along the restaurant according to its name
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        TextView tv = (TextView) view;
                        String str = tv.getText().toString();

                        for (int i = 0; i < restaurants.size(); i++) {
                            if (str == restaurants.get(i).getName()) {
//                                restaurants.get(i).describeContents();
                                Intent intent = new Intent(RestaurantListDisplay.this, RestaurantDisplay.class);
                                intent.putParcelableArrayListExtra("restaurants", restaurants);
                                intent.putExtra("index", i);
                                startActivity(intent);
                            }
                        }
                    }
                });

                textViews.add(textView);
            }
        }
    }
}
