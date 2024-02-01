package com.example.movies_nicolay_nacaro.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="tickets_table")
public class Ticket {
    private String movieTitle;
    @PrimaryKey
    private int movieID;
    private int quantity;
    private String date;


public Ticket(String movieTitle,int movieID,int quantity, String date){
    this.movieTitle = movieTitle;
    this.movieID = movieID;
    this.quantity = quantity;
    this.date = date;
}



    public int getMovieID() {
        return movieID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMovieTitle() {return movieTitle;}
    public String getDate(){return date;}
    public void addTick(){
    this.quantity++;
    }
}
