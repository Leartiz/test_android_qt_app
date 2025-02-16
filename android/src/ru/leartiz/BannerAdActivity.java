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

public class BannerAdActivity extends QtActivity {
    static private BannerAdActivity mainInstance = null;

    static private final String TAG = BannerAdActivity.class.getSimpleName();
    static private final String DEMO_AD_UNIT_ID = "demo-banner-yandex";
    static private String AD_UNIT_ID = "";

    private BannerAdView m_bannerAdView = null;
    private boolean m_isAdBannerShowed = false;
    private boolean m_isAdSizeConfigured = false;
    private boolean m_isAdUnitIdConfigured = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mainInstance = this;
        Log.d(TAG, "created");

        // ***

        ScreenUtils.logScreenSize(this);
        ScreenUtils.logVersionCodes();

        // ***

        // test demo:
        {
            initializeBannerAdView();
            setBannerAdUnitId(DEMO_AD_UNIT_ID);
            setBannerAdSize(320, 50);
            //...
        }
    }

    // public
    // -------------------------------------------------------------------

    public static void showToast(Context context, String message) {
        if (context != null && message != null) {
            Toast.makeText(context, message,
                           Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Context or message is null");
        }
    }

    public boolean isMainThread() {
        return Thread.currentThread() ==
            Looper.getMainLooper().getThread();
    }

    public void logAboutBannerAdView() {
        final String bannerAdViewTag = BannerAdView.class.getSimpleName();
        {
            Log.d(bannerAdViewTag, "Is Shown: " + m_bannerAdView.isShown());
            Log.d(bannerAdViewTag, "Visibility: " + m_bannerAdView.getVisibility());

            Log.d(bannerAdViewTag, "Width: " + m_bannerAdView.getWidth());
            Log.d(bannerAdViewTag, "Height: " + m_bannerAdView.getHeight());

            Log.d(bannerAdViewTag, "Measured width: " + m_bannerAdView.getMeasuredWidth());
            Log.d(bannerAdViewTag, "Measured height: " + m_bannerAdView.getMeasuredHeight());

            Log.d(bannerAdViewTag, "Parent: " + m_bannerAdView.getParent());
        }
    }

    // private
    // -------------------------------------------------------------------

    private boolean areAdParametersReadyForRequest() {
        if (m_bannerAdView == null) {
            return false;
        }

        return m_isAdSizeConfigured &&
               m_isAdUnitIdConfigured;
    }

    private boolean isBannerAdLoaded() {
        return false;
    }

    private void requestBannerAd() {
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        {
            //adRequestBuilder.setAge("14");
        }
        AdRequest adRequest = adRequestBuilder.build();
        m_bannerAdView.loadAd(adRequest);
    }

    // ad parameters
    // -------------------------------------------------------------------

    public void setBannerAdUnitId(final String adUnitId) {
        if (m_bannerAdView == null) {
            Log.w(TAG, "BannerAdView is not created. Cannot set AdUnitId.");
            return;
        }

        // ***

        runOnUiThread(new Runnable() {
            public void run() {

                //
                // Sets a valid ad unit ID.
                // Ad unit ID can be set only once.
                //
                m_bannerAdView.setAdUnitId(adUnitId);
                m_isAdUnitIdConfigured = true;

                if (areAdParametersReadyForRequest() &&
                    !isBannerAdLoaded()) {

                    mainInstance.requestBannerAd();
                }
            }
        });
    }

    public void setBannerAdSize(Integer width, Integer maxHeight) {
        if (m_bannerAdView == null) {
            Log.w(TAG, "BannerAdView is not created. Cannot set AdSize.");
            return;
        }

        // ***

        runOnUiThread(new Runnable() {
            public void run() {
                // !
                BannerAdSize bannerAdSize = BannerAdSize.inlineSize(
                    mainInstance,
                    width, maxHeight
                );

                //
                // BannerAdSize can be set only once.
                //
                m_bannerAdView.setAdSize(bannerAdSize);
                m_isAdSizeConfigured = true;

                if (areAdParametersReadyForRequest() &&
                    !isBannerAdLoaded()) {

                    mainInstance.requestBannerAd();
                }
            }
        });
    }

    public void setBannerAdPosition(float x, float y) {
        if (m_bannerAdView == null) {
            Log.w(TAG, "BannerAdView is not created. Cannot set AdSize.");
            return;
        }

        runOnUiThread(new Runnable() {
            public void run() {
                m_bannerAdView.setX(x);
                m_bannerAdView.setY(y);
            }
        });
    }

    // -------------------------------------------------------------------

    public void initializeBannerAdView() {
        if (m_bannerAdView != null) {
            Log.w(TAG, "BannerAdView has already been initialized");
            return;
        }

        // ***

        m_bannerAdView = new BannerAdView(this);
        {
            Log.d(TAG, "m_bannerAdView.info: " + m_bannerAdView.getInfo());
            Log.d(TAG, "m_bannerAdView.adSize: " + m_bannerAdView.getAdSize());
            //...
        }

        // ***

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        );

        // consts:
        {
            Log.d(TAG, "FrameLayout.LayoutParams.MATCH_PARENT: " +
                  FrameLayout.LayoutParams.MATCH_PARENT); // -1
            Log.d(TAG, "FrameLayout.LayoutParams.WRAP_CONTENT: " +
                  FrameLayout.LayoutParams.WRAP_CONTENT); // -2

            Log.d(TAG, "View.VISIBLE: " + View.VISIBLE);
            Log.d(TAG, "View.INVISIBLE: " + View.INVISIBLE);
            Log.d(TAG, "View.GONE: " + View.GONE);

            Log.d(TAG, "FrameLayout.VISIBLE: " + FrameLayout.VISIBLE);
            Log.d(TAG, "FrameLayout.INVISIBLE: " + FrameLayout.INVISIBLE);
            Log.d(TAG, "FrameLayout.GONE: " + FrameLayout.GONE);
        }

        m_bannerAdView.setLayoutParams(params);
        m_bannerAdView.setX(0);
        m_bannerAdView.setY(1000);

        // ***

        FrameLayout rootLayout = findViewById(android.R.id.content);
        rootLayout.addView(m_bannerAdView);

        // ***

        m_bannerAdView.setBannerAdEventListener(
            new BannerAdEventListener() {
                @Override
                public void onAdLoaded() {
                    Log.d(TAG, "Ad loaded.");
                    logAboutBannerAdView();
                }

                @Override
                public void onAdFailedToLoad(AdRequestError error) {
                    Log.e(TAG, "Ad failed to load: " + error);
                }

                @Override
                public void onAdClicked() {
                    Log.d(TAG, "Ad clicked.");
                }

                @Override
                public void onLeftApplication() {
                    Log.d(TAG, "Left application.");
                }

                @Override
                public void onReturnedToApplication() {
                    Log.d(TAG, "Returned to application.");
                }

                @Override
                public void onImpression(ImpressionData impressionData) {
                    Log.d(TAG, "Impression.");
                }
            }
        );
    }

    // -------------------------------------------------------------------

    public void showBannerAd() {
        runOnUiThread(() -> {
            if (m_bannerAdView != null) {
                m_bannerAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideBannerAd() {
        runOnUiThread(() -> {
            if (m_bannerAdView != null) {
                m_bannerAdView.setVisibility(View.GONE);
            }
        });
    }
}
