package com.sarpsolakoglu.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;

public class Client {

    public interface MovieDatabaseService {
        @GET("movie/popular")
        Call<MoviesResponse> popularMovies();
        @GET("movie/top_rated")
        Call<MoviesResponse> topRatedMovies();
    }

    private static Client instance = null;
    private static final String mBaseURL = "http://api.themoviedb.org/3/";

    // variable of type String
    public MovieDatabaseService movieDatabaseService;

    // private constructor restricted to this class itself
    private Client()
    {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.APIKEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        }).build();

        movieDatabaseService = new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MovieDatabaseService.class);
    }

    // static method to create instance of Singleton class
    public static Client getInstance()
    {
        if (instance == null)
            instance = new Client();

        return instance;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
 }
