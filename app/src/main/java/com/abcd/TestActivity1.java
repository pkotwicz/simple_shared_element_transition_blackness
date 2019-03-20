package com.abcd;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public class TestActivity1 extends android.support.v7.app.AppCompatActivity {
    private View mV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mV = LayoutInflater.from(this).inflate(
                com.abcd.R.layout.activity_main, null, false);
        setContentView(mV);
    }

    private boolean mB;

    @Override
    public void onEnterAnimationComplete() {
        if (mB) return;
        mB = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        }, 1);
    }

    private void launch() {
        Bundle bundle = android.support.v4.app.ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mV, "profile")
                .toBundle();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.abcd", "com.abcd.TestActivity2"));
        startActivity(intent, bundle);
    }
}