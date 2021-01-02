package com.movieziv.moviefirst.model.retrofit;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movieziv.moviefirst.R;

public class GlideImage {
    public static void getImageFromURL(String url, Context context, ImageView imageView) {
        String poster = "https://image.tmdb.org/t/p/w500" + url;
        Glide.with(context)
                .load(poster)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }
}
