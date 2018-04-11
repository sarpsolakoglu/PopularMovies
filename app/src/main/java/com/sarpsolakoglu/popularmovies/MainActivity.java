package com.sarpsolakoglu.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.movie_list) RecyclerView movieList;

    private MovieAdapter mAdapter;

    private Callback<MoviesResponse> moviesResponseCallback = new Callback<MoviesResponse>() {
        @Override
        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
            handleResponse(response);
        }

        @Override
        public void onFailure(Call<MoviesResponse> call, Throwable t) {
            handleFailure();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void fetchPopular() {
        Client.getInstance().movieDatabaseService.popularMovies().enqueue(moviesResponseCallback);
    }

    public void fetchTopRated() {
        Client.getInstance().movieDatabaseService.topRatedMovies().enqueue(moviesResponseCallback);
    }

    public void handleResponse(Response<MoviesResponse> response) {
        MoviesResponse moviesResponse = response.body();
        if (moviesResponse != null && !moviesResponse.results.isEmpty()) {
            handleSuccess(moviesResponse.results);
        } else {
            handleFailure();
        }
    }

    public void handleFailure() {
        Log.d(TAG, "Failed");
    }

    public void handleSuccess(List<Movie> movies) {
        mAdapter.setDataSource(movies);
    }
}
