package com.android.cen.andrew.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

public class Thumbnail {
    private Bitmap mBitmap;
    private String mTitle;
    private String mPath;
    private boolean mFolder;

    public Thumbnail(Bitmap bitmap, String title, String path) {
        mBitmap = bitmap;
        mTitle = title;
        mPath = path;
        mFolder = false;
    }

    public Thumbnail(String title, String path, Context context) {
        mBitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(context.getResources(), R.drawable.folder)
                , 350, 350);
        mTitle = title;
        mPath = path;
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

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }
}
