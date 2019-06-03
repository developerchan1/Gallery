package com.android.cen.andrew.gallery;

import android.graphics.Bitmap;

public class Thumbnail {
    private Bitmap mBitmap;
    private String mTitle;
    private boolean mFolder;

    public Thumbnail(Bitmap bitmap, String title) {
        mBitmap = bitmap;
        mTitle = title;
        mFolder = false;
    }

    public Thumbnail(String title) {
        mTitle = title;
        mFolder = true;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isFolder() {
        return mFolder;
    }

    public void setFolder(boolean folder) {
        mFolder = folder;
    }
}
