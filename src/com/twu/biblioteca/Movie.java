package com.twu.biblioteca;

class Movie extends libraryObject{

    private String name, director;
    private int year, rating;

    public Movie(String n, int y, String d, int r){
        name = n;
        director = d;
        year = y;
        rating = r;
    }

    @Override
    public String toString(){
        return name + " || " + year + " || " + director + " || " + rating;
    }

}
