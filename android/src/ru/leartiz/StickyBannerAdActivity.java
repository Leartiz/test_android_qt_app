package ru.leartiz;

import android.util.Log;
import android.os.Bundle;

import org.qtproject.qt.android.bindings.QtActivity;

public class StickyBannerAdActivity extends QtActivity
{
    private static final String TAG =
        StickyBannerAdActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "created");
    }
}
