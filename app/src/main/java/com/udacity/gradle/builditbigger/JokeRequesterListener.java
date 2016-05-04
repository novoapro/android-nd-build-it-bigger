package com.udacity.gradle.builditbigger;

/**
 * novoa on 5/4/16.
 */
public interface JokeRequesterListener {
    void onSuccess(String joke);
    void onError();
}
