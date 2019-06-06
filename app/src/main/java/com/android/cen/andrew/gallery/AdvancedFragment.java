package com.android.cen.andrew.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdvancedFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ThumbnailAdapter mAdapter;
    private TextView mErrorTextView;
    private List<Thumbnail> mThumbnails;
    private List<String> mPathSequence;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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
                        if (mThumbnail.getTitle().equals("../")) {
                            mPathSequence.remove(mPathSequence.size()-1);
                        } else {
                            mPathSequence.add(mThumbnail.getTitle());
                        }
                        loadAdvanced();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        mThumbnails = new ArrayList<>();
        mPathSequence = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mErrorTextView = view.findViewById(R.id.error_text_view);
        loadAdvanced();

        mSwipeRefreshLayout = view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadAdvanced();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return view;
    }

    private void refreshRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new ThumbnailAdapter(mThumbnails, getContext());
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void loadAdvanced() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Advanced";

        if (!mPathSequence.isEmpty()) {
            for (String p : mPathSequence) {
                path += "/" + p;
            }
        }

        File file = new File(path);
        File[] files = file.listFiles();

        mErrorTextView.setVisibility(View.GONE);
        mThumbnails.clear();

        if (!mPathSequence.isEmpty()) {
            mThumbnails.add(new Thumbnail("../", null, getContext()));
        }

        if (files.length == 0 && mThumbnails.isEmpty()) {
            mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }

        for (File f : files) {
            if (f.exists() && ImageFileFilter.check(f)) {
                Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(f.getPath()), 350, 350);
                mThumbnails.add(new Thumbnail(bitmap, f.getName(), f.getPath()));
            } else if (f.exists() && f.isDirectory()) {
                mThumbnails.add(new Thumbnail(f.getName(), f.getPath(), getContext()));
            }
        }

        refreshRecyclerView();
    }
}
