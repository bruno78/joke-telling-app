package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.brunogtavares.displayjoke.DisplayJokeActivity;
import com.brunogtavares.jokeslib.Jokes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This method retrieves jokes directly from the Java Library and send to
    // Android Library to be displayed
//    public void tellJoke(View view) {
//        Jokes joker = new Jokes();
//        String joke = joker.getJoke();
//        // Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
//
//        sendJokeToDisplayLibrary(joke);
//    }

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
