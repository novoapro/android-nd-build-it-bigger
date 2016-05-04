package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.manpdev.joketeller.JokeViewerActivity;


public class MainActivity extends AppCompatActivity implements JokeRequesterListener {

    private Intent mJokeIntent;
    private JokeRequesterAsync mRequester;
    private ProgressDialog mProgressDialog;

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
    protected void onStart() {
        super.onStart();
        if (mProgressDialog != null)
            this.mProgressDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequester.setListener(null);
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
        showProgressDialog();
        mRequester = new JokeRequesterAsync();
        mRequester.setListener(this);
        mRequester.execute("");
    }


    @Override
    public void onSuccess(String joke) {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();

        tellJoke(joke);
    }

    @Override
    public void onError() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        Toast.makeText(MainActivity.this, "Error retrieving joke", Toast.LENGTH_LONG).show();
    }

    private void showProgressDialog() {
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setIndeterminate(true);
        this.mProgressDialog.setMessage("Getting joke...");
        this.mProgressDialog.show();
    }
}
