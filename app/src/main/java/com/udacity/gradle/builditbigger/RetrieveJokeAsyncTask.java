package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by brunogtavares on 9/8/18.
 */

public class RetrieveJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private final static String ROOT_URL = "http://10.0.2.2:8080/_ah/api/";

    private OnJokeLoaded mCallback;
    private static MyApi myApiService = null;

    public interface OnJokeLoaded {
        void success(String result);
    }

    public RetrieveJokeAsyncTask(){}

    public RetrieveJokeAsyncTask setListener(OnJokeLoaded callback) {
        mCallback = callback;
        return this;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();

        }

        try {
            return myApiService.loadJoke().execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.success(result);
    }
}
