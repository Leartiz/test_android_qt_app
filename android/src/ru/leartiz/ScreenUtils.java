package ru.leartiz;

import android.util.Log;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;

import android.view.WindowManager;
import android.view.Display;

public class ScreenUtils {
    public static Point getScreenSizeInPixels(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(
            Context.WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();
        Point screenSize = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(screenSize);
        }
        else {
            screenSize.y = display.getHeight();
            screenSize.x = display.getWidth();
        }

        return screenSize;
    }

    public static float convertPixelsToDp(Context context, int pixels) {
        float density = context.getResources().getDisplayMetrics().density;
        return pixels / density;
    }

    public static void logScreenSize(Context context) {
        Point screenSize = getScreenSizeInPixels(context);
        int widthInPixels = screenSize.x;
        int heightInPixels = screenSize.y;

        float widthInDp = convertPixelsToDp(context, widthInPixels);
        float heightInDp = convertPixelsToDp(context, heightInPixels);

        Log.d("Screen Size", "Width: " + widthInPixels + "px, Height: " + heightInPixels + "px");
        Log.d("Screen Size", "Width: " + widthInDp + "dp, Height: " + heightInDp + "dp");
    }
}
