package com.nedo.decider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView welcomeText;
    TextView decisionShuffleText;
    Random rand = new Random();

    String food_choices[] = {"pizza", "chinese", "japanese", "burgers", "subs",
                             "italian", "mexican", "thai", "greek", "sushi",
                             "seafood", "brazilian" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //decide(view);
                String location = "01453";
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }

    // Begin decision process on click of start button
    // TODO
    // Current idea is to have the decisionShuffleText change
    // continuously to simulate a roulette. Need to find a way
    // to do this asynchronously, as all changes made inside
    // the decide() function are only displayed after the
    // function returns.
    public void decide(View view)
    {
        welcomeText = findViewById(R.id.welcomeText);
        decisionShuffleText = findViewById(R.id.decisionShuffleText);

        // Change text to "deciding..."
        welcomeText.setText(R.string.decidingText);

        // TODO
        // Implement for loop to shuffle through the cuisines,
        // simulating a roulette.

        // choose random index
        int index = rand.nextInt(food_choices.length);
        // select cuisine based on random index
        decisionShuffleText.setText(food_choices[index]);
        decisionShuffleText.setVisibility(View.VISIBLE);

        welcomeText.setText(R.string.done);
    }
}
