package com.android.cen.andrew.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailHolder> {
    private List<Thumbnail> mThumbnails;
    private Context mContext;

    public ThumbnailAdapter(List<Thumbnail> thumbnails, Context context) {
        mThumbnails = thumbnails;
        mContext = context;
    }

    @NonNull
    @Override
    public ThumbnailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.thumbnail, parent, false);
        return new ThumbnailHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailHolder holder, int position) {
        holder.bind(mThumbnails.get(position));
    }

    @Override
    public int getItemCount() {
        return mThumbnails.size();
    }
}
