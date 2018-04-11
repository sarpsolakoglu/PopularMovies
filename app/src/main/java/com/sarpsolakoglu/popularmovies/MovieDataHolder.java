package com.sarpsolakoglu.popularmovies;

public class MovieDataHolder {
    private String imageURL;

    public MovieDataHolder(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
