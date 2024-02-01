package com.example.movies_nicolay_nacaro.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movies implements Serializable {
    public boolean adult;
    public String backdrop_path;
@SerializedName("genre_ids")
    List<Integer> genre_ids;
public int id;
public String original_language;
    public String original_title;
    public String overview;
public double popularity;

    public String poster_path;
    public String release_date;
public String  title;
public boolean video;
public double vote_average;
    public int vote_count;
    public String getName(){
        return original_title;
    }
    public String getDesc(){
        return overview;
    }
    public String getUrl(){
        return "https://image.tmdb.org/t/p/original"+ poster_path;
    }
    public String getRating(){
        return Math.round(vote_average * 10) +"";
    }
    public String getReleased(){
        return "Released:" + release_date;
    }
    public int getID(){
        return id;
    }
}