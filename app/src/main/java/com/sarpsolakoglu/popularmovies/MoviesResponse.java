package com.sarpsolakoglu.popularmovies;

import java.util.List;

public class MoviesResponse {
    public final List<Movie> results;

    public MoviesResponse(List<Movie> results) {
        this.results = results;
    }
}
