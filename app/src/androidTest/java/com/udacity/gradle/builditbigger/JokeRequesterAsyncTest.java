package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;

/**
 * novoa on 5/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class JokeRequesterAsyncTest{

    private JokeRequesterAsync mRequester;

    @Before
    public void setUp() throws Exception {
        this.mRequester  = new JokeRequesterAsync();
    }

    @After
    public void tearDown() throws Exception {
        this.mRequester = null;
    }

    @Test
    public void checkResult()throws Exception{
        this.mRequester.execute("");
        assertFalse(TextUtils.isEmpty(this.mRequester.get()));
    }
}