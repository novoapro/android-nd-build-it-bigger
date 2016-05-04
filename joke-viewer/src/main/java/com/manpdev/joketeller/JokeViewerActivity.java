package com.manpdev.joketeller;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.manpdev.joketeller.databinding.ActivityJokeViewerBinding;

public class JokeViewerActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";
    public static final String JOKE_STATE = "joke_state";

    private ActivityJokeViewerBinding mActivityBinding;
    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_joke_viewer);

        if (savedInstanceState == null)
            this.mJoke = getIntent().getStringExtra(JOKE_EXTRA);
        else
            this.mJoke = savedInstanceState.getString(JOKE_STATE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mJoke = getIntent().getStringExtra(JOKE_EXTRA);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mActivityBinding.tvJokeViewer.setText(mJoke);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(mJoke))
            outState.putString(JOKE_STATE, mJoke);
    }
}
