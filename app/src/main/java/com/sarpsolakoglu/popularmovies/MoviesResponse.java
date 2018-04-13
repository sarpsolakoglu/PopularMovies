package com.sarpsolakoglu.popularmovies;

import java.util.List;

class MoviesResponse {
    private final List<Movie> results;

    public MoviesResponse(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
