package ru.leartiz;

import android.util.Log;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;

import android.util.Log;

import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;

public class ScreenUtils {

    static private final String TAG =
        ScreenUtils.class.getSimpleName();

    // -------------------------------------------------------------------

    public static Point getScreenSizeInPixels(Context context) {
        Display display = context.getDisplay();
        Point screenSize = new Point();

        // This method was deprecated in API level 30
        display.getSize(screenSize);
        return screenSize;
    }

    public static float convertPixelsToDp(Context context, int pixels) {
        float density = context.getResources().getDisplayMetrics().density;
        return pixels / density;
    }

    // in pixels
    // -------------------------------------------------------------------

    public static int getWidthInPixels(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        return screenSize.x;
    }

    public static int getHeightInPixels(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        return screenSize.y;
    }

    // in pixels
    // -------------------------------------------------------------------

    public static float getWidthInDp(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        return convertPixelsToDp(context, screenSize.x);
    }

    public static float getHeightInDp(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        return convertPixelsToDp(context, screenSize.y);
    }

    // -------------------------------------------------------------------

    public static void logScreenSize(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        int widthInPixels = screenSize.x;
        int heightInPixels = screenSize.y;

        float widthInDp = convertPixelsToDp(context, widthInPixels);
        float heightInDp = convertPixelsToDp(context, heightInPixels);

        // ***

        Log.d(TAG, "Width: " + widthInPixels +   "px, " +
                   "Height: " + heightInPixels + "px");

        Log.d(TAG, "Width: " + widthInDp +   "dp, " +
                   "Height: " + heightInDp + "dp");
    }

    public static void logVersionCodes() {
        Log.d(TAG, "VERSION_CODES.P: " + Build.VERSION_CODES.P);
        Log.d(TAG, "VERSION_CODES.Q: " + Build.VERSION_CODES.Q);
        Log.d(TAG, "VERSION_CODES.R: " + Build.VERSION_CODES.R);
        Log.d(TAG, "VERSION_CODES.S: " + Build.VERSION_CODES.S);
        Log.d(TAG, "VERSION_CODES.S_V2: " + Build.VERSION_CODES.S_V2);
        Log.d(TAG, "VERSION_CODES.TIRAMISU: " + Build.VERSION_CODES.TIRAMISU);
        Log.d(TAG, "VERSION_CODES.UPSIDE_DOWN_CAKE: " + Build.VERSION_CODES.UPSIDE_DOWN_CAKE);
        //...
    }
}
