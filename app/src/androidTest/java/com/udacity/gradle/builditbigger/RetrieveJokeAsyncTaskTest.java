package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by brunogtavares on 9/9/18.
 * https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
 */
@RunWith(AndroidJUnit4.class)
public class RetrieveJokeAsyncTaskTest {

    @Test
    public void testIfAsyncTaskRetrievesANonEmptyString() {

        // CountDownLatch signal objects to implement the wait-notifiy
        final CountDownLatch signal = new CountDownLatch(1);

        RetrieveJokeAsyncTask task = new RetrieveJokeAsyncTask();
        task.setListener(new RetrieveJokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {

                assertTrue("The retrieved string is not empty or null",
                        result != null && !result.isEmpty());
                assertFalse("The retrieved string is not an error message",
                        result.equalsIgnoreCase(RetrieveJokeAsyncTask.ERROR_MESSAGE));

                signal.countDown(); // notify the count down latch
            }
        }).execute();

        try {
            signal.await(); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
            String error = e.getMessage();
            assertFalse("The retrieved string is not an error message", error.equalsIgnoreCase(e.getMessage()));
        }
    }
}