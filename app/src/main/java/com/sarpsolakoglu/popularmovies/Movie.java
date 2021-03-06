package com.sarpsolakoglu.popularmovies;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private final String original_title;
    private final String overview;
    private final String poster_path;
    private final String release_date;
    private final Double vote_average;

    private Movie(Parcel parcel) {
        this.original_title = parcel.readString();
        this.overview = parcel.readString();
        this.poster_path = parcel.readString();
        this.release_date = parcel.readString();
        this.vote_average = parcel.readDouble();
    }

    // http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
    public String getImageURL() {
        return Uri.parse("http://image.tmdb.org/t/p/w185/").buildUpon()
                .appendEncodedPath(poster_path)
                .build().toString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[0];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeDouble(vote_average);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public Double getVoteAverage() {
        return vote_average;
    }
}
