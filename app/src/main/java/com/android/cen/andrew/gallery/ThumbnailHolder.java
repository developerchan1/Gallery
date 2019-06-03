package com.android.cen.andrew.gallery;

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

    public ThumbnailHolder(@NonNull View itemView) {
        super(itemView);
        mCardView = itemView.findViewById(R.id.card);
        mImageView = itemView.findViewById(R.id.thumbnail_image);
        mTextView = itemView.findViewById(R.id.thumbnail_text);
    }

    public void bind(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
        mImageView.setImageBitmap(mThumbnail.getBitmap());

        if (mThumbnail.isFolder()) {
            mTextView.setText(mThumbnail.getTitle());
        }

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
