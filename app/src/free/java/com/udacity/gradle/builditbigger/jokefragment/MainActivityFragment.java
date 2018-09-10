package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.brunogtavares.displayjoke.DisplayJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public PublisherInterstitialAd mInterstitialAd;
    private ProgressBar mLoadingIndicator;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingIndicator = root.findViewById(R.id.pb_loading);
        mLoadingIndicator.setVisibility(View.GONE);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        // Creating an Interstitial ad.
        if(getContext() != null)
        mInterstitialAd = new PublisherInterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("/6499/example/interstitial");
        mInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                super.onAdClosed();

                mLoadingIndicator.setVisibility(View.VISIBLE);

                tellJoke();

                // Load the next interstitial
                mInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

                Log.i(LOG_TAG, "Interstitial ad failed to load");

                // Load the next interstitial
                mInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        Button jokeButton = root.findViewById(R.id.bt_joke);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    tellJoke();
                }
            }
        });

        return root;
    }


    // This method retrieves jokes from Google Cloud which retrieves from Java Library to be displayed
    public void tellJoke() {
        RetrieveJokeAsyncTask task = new RetrieveJokeAsyncTask();

        task.setListener(new RetrieveJokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                sendJokeToDisplayLibrary(result);
            }
        }).execute();
    }

    private void sendJokeToDisplayLibrary(String joke) {
        Intent intent = new Intent(getContext(), DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.DISPLAY_JOKE_EXTRA, joke);

        mLoadingIndicator.setVisibility(View.GONE);

        startActivity(intent);
    }
}
