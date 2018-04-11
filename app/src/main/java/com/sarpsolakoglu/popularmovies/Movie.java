package com.sarpsolakoglu.popularmovies;

public class Movie {
    public final String original_title;
    public final String overview;
    public final String poster_path;
    public final String release_date;
    public final Double vote_average;

    public Movie(String original_title, String overview, String poster_path, String release_date, Double vote_average) {
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }
}
