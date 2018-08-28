package com.brunogtavares.jokeslib;

import org.junit.Test;

/**
 * Created by brunogtavares on 8/28/18.
 */

public class JokesTest {
    @Test
    public void getJokeTest() {
        Jokes joker = new Jokes();
        assert joker.getJoke() != null;
    }
}
