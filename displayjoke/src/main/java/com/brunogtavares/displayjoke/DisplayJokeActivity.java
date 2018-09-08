package com.brunogtavares.displayjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    public static final String DISPLAY_JOKE_EXTRA = "DISPLAY_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView displayJoke = findViewById(R.id.tv_joke_display);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(DISPLAY_JOKE_EXTRA)) {
            String joke = intent.getStringExtra(DISPLAY_JOKE_EXTRA);
            displayJoke.setText(joke);
        }
    }
}
