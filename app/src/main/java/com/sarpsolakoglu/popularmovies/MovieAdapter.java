package com.sarpsolakoglu.popularmovies;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public interface MovieAdapterOnClickListener {
        public void onClick(Movie movie);
    }

    private @NonNull List<Movie> dataSource = new ArrayList<>();

    private MovieAdapterOnClickListener onClickListener;

    public MovieAdapter(MovieAdapterOnClickListener onClickListener) {
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(@NonNull List<Movie> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }
}
