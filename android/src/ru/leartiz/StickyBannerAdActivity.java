package ru.leartiz;

import android.util.Log;
import android.widget.Toast;

import android.content.Context;
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

    // -------------------------------------------------------------------

    public static void showToast(Context context, String message) {
        if (context != null && message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Context or message is null");
        }
    }
}
