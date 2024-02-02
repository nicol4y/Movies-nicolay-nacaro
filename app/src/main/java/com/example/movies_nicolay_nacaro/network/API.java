package com.example.movies_nicolay_nacaro.network;

import com.example.movies_nicolay_nacaro.models.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://api.themoviedb.org/3/movie/";
    @GET("now_playing?api_key=")
    public Call<MovieResponse> getAllMovies();

}
// Replace the empty string with your own APi key
