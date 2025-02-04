package ru.leartiz;

import android.util.Log;

import android.widget.Toast;
import android.widget.FrameLayout;

import android.view.View;
import android.view.ViewGroup;
import android.util.DisplayMetrics;
import android.view.Gravity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.content.Context;

import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.banner.BannerAdEventListener;

import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;

import org.qtproject.qt.android.bindings.QtActivity;

public class StickyBannerAdActivity extends QtActivity {
    static private StickyBannerAdActivity mainInstance = null;

    private static final String TAG = StickyBannerAdActivity.class.getSimpleName();
    static private final String DEMO_AD_UNIT_ID = "demo-banner-yandex";

    private BannerAdView bannerAdView = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mainInstance = this;
        Log.d(TAG, "created");

        // ***

        setupBannerAd();
        showBannerAd();
    }

    // -------------------------------------------------------------------

    private void setupBannerAd() {
        bannerAdView = new BannerAdView(this);
        bannerAdView.setAdUnitId(DEMO_AD_UNIT_ID);
        {
            Log.d(TAG, "bannerAdView.info: " + bannerAdView.getInfo());
            Log.d(TAG, "bannerAdView.adSize: " + bannerAdView.getAdSize());
        }

        BannerAdSize bannerAdSize = BannerAdSize.inlineSize(this, 320, 50);
        bannerAdView.setAdSize(bannerAdSize);

        {
            Log.d(TAG, "FrameLayout.LayoutParams.MATCH_PARENT: " +
                FrameLayout.LayoutParams.MATCH_PARENT); // -1
            Log.d(TAG, "FrameLayout.LayoutParams.WRAP_CONTENT: " +
                FrameLayout.LayoutParams.WRAP_CONTENT); // -2
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
        );
        bannerAdView.setLayoutParams(params);

        // ***

        FrameLayout rootLayout = findViewById(android.R.id.content);
        rootLayout.addView(bannerAdView);

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        bannerAdView.loadAd(adRequestBuilder.build());
    }

    public void showBannerAd() {
        runOnUiThread(() -> {
            if (bannerAdView != null) {
                bannerAdView.setVisibility(FrameLayout.VISIBLE);
            }
        });
    }

    public static void showToast(Context context, String message) {
        if (context != null && message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Context or message is null");
        }
    }
}
