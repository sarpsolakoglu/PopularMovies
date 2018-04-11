package com.sarpsolakoglu.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_image_view) ImageView mImageView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void onBind(MovieDataHolder dataHolder) {
        Picasso.get().load(dataHolder.getImageURL()).centerCrop().into(mImageView);
    }
}