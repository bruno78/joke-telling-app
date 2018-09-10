package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brunogtavares.displayjoke.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method retrieves jokes from Google Cloud which retrieves from Java Library to be displayed
    public void tellJoke(View view) {
        RetrieveJokeAsyncTask task = new RetrieveJokeAsyncTask();

        task.setListener(new RetrieveJokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                sendJokeToDisplayLibrary(result);
            }
        }).execute();
    }

    private void sendJokeToDisplayLibrary(String joke) {
        Intent intent = new Intent(this, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.DISPLAY_JOKE_EXTRA, joke);
        startActivity(intent);
    }


}
