package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.manpdev.joketeller.*;
import com.manpdev.joketeller.backend.jokeApi.JokeApi;
import com.manpdev.joketeller.backend.jokeApi.model.JokeModel;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Intent mJokeIntent;
    private JokeApi mJokeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JokeApi.Builder builder =
                new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);

        this.mJokeApi = builder.build();
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

    public void tellJoke(String joke) {
        if (mJokeIntent == null)
            mJokeIntent = new Intent(this, JokeViewerActivity.class);

        mJokeIntent.putExtra(JokeViewerActivity.JOKE_EXTRA, joke);
        startActivity(mJokeIntent);
    }

    public void requestJoke(View view) {
        new JokeRequesterAsync().execute("");
    }

    public class JokeRequesterAsync extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
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
            if (!TextUtils.isEmpty(result))
                tellJoke(result);
            else
                Toast.makeText(MainActivity.this, "No jokes", Toast.LENGTH_LONG).show();
        }
    }


}
