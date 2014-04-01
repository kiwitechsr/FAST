package org.ligi.fast.ui;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by: samir
 * Date: 3/31/14
 * Time: 7:05 PM
 */
public final class TYPEFACE {
    private static Typeface mHalloRegularTypeface;
    private static Typeface mHalloBlackTypeface;
    private static Typeface mHalloLightTypeface;

    public static void init(Context ctx) {
        if (ctx == null) return;
        if (mHalloRegularTypeface == null)
            mHalloRegularTypeface = Typeface.createFromAsset(
                    ctx.getApplicationContext().getAssets(), "hallo_sans_regular.otf");
        if (mHalloBlackTypeface == null)
            mHalloBlackTypeface = Typeface.createFromAsset(
                    ctx.getApplicationContext().getAssets(), "hallo_sans_black.otf");
        if (mHalloLightTypeface == null)
            mHalloLightTypeface = Typeface.createFromAsset(
                    ctx.getApplicationContext().getAssets(), "hallo_sans_light.otf");
    }

    public static Typeface halloRegular() {
        return mHalloRegularTypeface;
    }
    public static Typeface halloBlack() {
        return mHalloBlackTypeface;
    }
    public static Typeface halloLight() {
        return mHalloLightTypeface;
    }
}