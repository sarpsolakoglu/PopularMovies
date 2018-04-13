package com.sarpsolakoglu.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    private static final String TAG = MovieActivity.class.getSimpleName();

    @BindView(R.id.movie_details_image_view) ImageView mPosterImageView;
    @BindView(R.id.overview_text_view) TextView mOverviewTextView;
    @BindView(R.id.rating_value_text_view) TextView mRatingTextView;
    @BindView(R.id.release_data_value_text_view) TextView mReleaseDateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.MOVIE_EXTRA)) {
            Movie movie = intent.getParcelableExtra(Constants.MOVIE_EXTRA);
            prepareUI(movie);
        }
    }

    private void prepareUI(Movie movie) {
        setTitle(movie.getOriginalTitle());
        Picasso.get().load(movie.getImageURL()).into(mPosterImageView);
        mOverviewTextView.setText(movie.getOverview());
        mRatingTextView.setText(String.valueOf(movie.getVoteAverage()));
        mReleaseDateTextView.setText(movie.getReleaseDate());
    }
}
