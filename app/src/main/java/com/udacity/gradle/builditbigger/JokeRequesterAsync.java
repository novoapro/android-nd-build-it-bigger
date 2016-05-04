package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.manpdev.joketeller.backend.jokeApi.JokeApi;
import com.manpdev.joketeller.backend.jokeApi.model.JokeModel;

import java.io.IOException;

/**
 * novoa on 5/4/16.
 */
public class JokeRequesterAsync extends AsyncTask<String, String, String> {
    private static final String TAG = "JokeRequesterAsync";
    private JokeRequesterListener mListener;

    public void setListener(JokeRequesterListener listener) {
        this.mListener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            JokeApi.Builder builder =
                    new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);

            JokeApi mJokeApi = builder.build();

            JokeModel model = mJokeApi.getJoke().execute();
            if (model != null)
                return model.getJoke();
            else
                return null;
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (mListener != null) {
            if (result != null)
                mListener.onSuccess(result);
            else
                mListener.onError();
        }
    }
}
