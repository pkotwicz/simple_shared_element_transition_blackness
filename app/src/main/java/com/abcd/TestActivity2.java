package com.abcd;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

public class TestActivity2 extends android.support.v7.app.AppCompatActivity {
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        super.onCreate(savedInstanceState);

        final View mV = LayoutInflater.from(this).inflate(
                R.layout.activity_main, null, false);
        setContentView(mV);
        mV.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                startPostponedEnterTransition();
                mV.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        setEnterSharedElementCallback(new android.app.SharedElementCallback() {
            @Override
            public void onMapSharedElements(java.util.List<String> names,
                                            java.util.Map<String, android.view.View> sharedElements) {
                android.util.Log.e("ABCD", "onMapSharedElements " + names.size()  + " " + sharedElements.size());
                sharedElements.put("profile", mV);
                super.onMapSharedElements(names, sharedElements);
            }
        });
    }
}
