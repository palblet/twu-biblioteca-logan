package com.twu.biblioteca;

class Book extends library {

    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String bookTitle(){
        return title;
    }

    @Override
    public String toString() {
        return title + " || " + author + " || " + yearPublished;
    }
}
