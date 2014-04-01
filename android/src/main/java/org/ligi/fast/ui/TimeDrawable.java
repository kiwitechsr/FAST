package org.ligi.fast.ui;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: samir
 * Date: 3/31/14
 * Time: 7:04 PM
 */
public class TimeDrawable extends Drawable{
    private final Paint mFontPaint;

    public TimeDrawable() {
        mFontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFontPaint.setTextAlign(Paint.Align.LEFT);
        mFontPaint.setStyle(Paint.Style.FILL);
        mFontPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas)
    {
        Rect rect = new Rect();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("h");
        String hour = sdf.format(date);
        sdf.applyPattern("mm");
        String min = sdf.format(date);
        sdf.applyPattern("a");
        String ampm = sdf.format(date);

        sdf.applyPattern("EEE, MMMM dd");
        String day = sdf.format(date).toUpperCase();

        int width = 0;

        mFontPaint.setTextAlign(Paint.Align.LEFT);
        mFontPaint.setColor(0xfff3f3f3);
        mFontPaint.setTextSize(120);
        mFontPaint.setTypeface(TYPEFACE.halloBlack());
        mFontPaint.getTextBounds(hour, 0, hour.length(), rect);
        width += rect.width() + 10;

        mFontPaint.setTypeface(TYPEFACE.halloLight());
        mFontPaint.getTextBounds(":", 0, 1, rect);
        width+= rect.width() + 10;

        mFontPaint.setTextSize(128);
        mFontPaint.setTypeface(TYPEFACE.halloRegular());
        mFontPaint.getTextBounds(min, 0, min.length(), rect);
        width+= rect.width() + 40;

        mFontPaint.setTextSize(72);
        mFontPaint.setTypeface(TYPEFACE.halloBlack());
        mFontPaint.getTextBounds(ampm, 0, ampm.length(), rect);
        width+=rect.width();

        float pos = canvas.getWidth() * 0.5f - width * 0.5f;

        mFontPaint.setTextSize(120);
        mFontPaint.setTypeface(TYPEFACE.halloBlack());
        canvas.drawText(hour, pos, canvas.getHeight() * 0.5f + 20f, mFontPaint);
        mFontPaint.getTextBounds(hour, 0, hour.length(), rect);
        pos += rect.width() + 10;

        mFontPaint.setTypeface(TYPEFACE.halloLight());
        canvas.drawText(":", pos, canvas.getHeight() * 0.5f + 20f, mFontPaint);
        mFontPaint.getTextBounds(":", 0, 1, rect);
        pos += rect.width() + 10;

        mFontPaint.setTextSize(128);
        mFontPaint.setTypeface(TYPEFACE.halloRegular());
        canvas.drawText(min, pos, canvas.getHeight() * 0.5f + 20f, mFontPaint);
        mFontPaint.getTextBounds(min, 0, min.length(), rect);
        pos += rect.width() + 40;

        mFontPaint.setTextSize(72);
        mFontPaint.setTypeface(TYPEFACE.halloBlack());
        canvas.drawText(ampm, pos, canvas.getHeight() * 0.5f + 20f, mFontPaint);
        mFontPaint.getTextBounds(ampm, 0, ampm.length(), rect);

        mFontPaint.setTextSize(40);
        mFontPaint.setTypeface(TYPEFACE.halloLight());
        mFontPaint.setColor(0xffb2b2b2);
        mFontPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(day, canvas.getWidth() * 0.5f, canvas.getHeight() * 0.5f + 80f, mFontPaint);
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int arg0) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}
}
