package com.twu.biblioteca;

class Movie extends libraryObject{

    private String name, director;
    private int year, rating;

    public Movie(String name, int year, String director, int rating){
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString(){
        return name + " || " + year + " || " + director + " || " + rating;
    }

}
