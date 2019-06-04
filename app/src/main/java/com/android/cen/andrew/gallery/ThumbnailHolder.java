package com.android.cen.andrew.gallery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

public class ThumbnailHolder extends RecyclerView.ViewHolder {
    private MaterialCardView mCardView;
    private ImageView mImageView;
    private TextView mTextView;
    private Thumbnail mThumbnail;
    private Context mContext;

    public ThumbnailHolder(@NonNull View itemView, Context context) {
        super(itemView);
        mCardView = itemView.findViewById(R.id.card);
        mImageView = itemView.findViewById(R.id.thumbnail_image);
        mTextView = itemView.findViewById(R.id.thumbnail_text);
        mContext = context;
    }

    public void bind(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
        mImageView.setImageBitmap(mThumbnail.getBitmap());

        if (mThumbnail.isFolder()) {
            mTextView.setText(mThumbnail.getTitle());
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = ImageActivity.newInstance(mContext, mThumbnail.getTitle(), mThumbnail.getPath());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
