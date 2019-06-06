package com.android.cen.andrew.gallery;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenSize {

    public static int getWidth(Context context) {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point outMetrics = new Point();
        display.getSize(outMetrics);

        return outMetrics.x;
    }

    public static int getHeight(Context context) {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point outMetrics = new Point();
        display.getSize(outMetrics);

        return outMetrics.y;
    }
}
