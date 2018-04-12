package com.sarpsolakoglu.popularmovies;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface MovieAdapterOnClickListener {
        void onClick(Movie movie);
    }

    private @NonNull List<Movie> dataSource = new ArrayList<>();

    private MovieAdapterOnClickListener onClickListener;

    MovieAdapter(MovieAdapterOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_cell, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Configure height of cell
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        params.height = metrics.widthPixels / Constants.SPAN_COUNT * 277 / 185;
        holder.itemView.setLayoutParams(params);
        // Get movie
        final Movie movie = dataSource.get(position);
        // Bind data to cell
        holder.onBind(movie);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(@NonNull List<Movie> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_image_view)
        ImageView mImageView;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void onBind(Movie data) {
            Picasso.get().load(data.getImageURL()).into(mImageView);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(dataSource.get(getAdapterPosition()));
        }
    }
}
