package com.android.cen.andrew.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ImageActivity extends AppCompatActivity {
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_PATH = "path";
    private Toolbar mToolbar;
    private ImageView mImageView;
    private String mTitle;
    private String mPath;

    public static Intent newInstance(Context context, String title, String path) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_PATH, path);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
        mPath = getIntent().getStringExtra(EXTRA_PATH);

        mToolbar = findViewById(R.id.toolbar_image);
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageView = findViewById(R.id.image_view);
        mImageView.setImageBitmap(BitmapFactory.decodeFile(mPath));
    }
}
