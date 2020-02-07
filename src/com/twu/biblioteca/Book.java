package com.twu.biblioteca;

class Book extends libraryObject{

    private String title;
    private String author;
    private int yearPublished;

    public Book(String t, String a, int i) {
        title = t;
        author = a;
        yearPublished = i;
    }

    public String bookTitle(){
        return title;
    }

    @Override
    public String toString() {
        return title + " || " + author + " || " + yearPublished;
    }
}
