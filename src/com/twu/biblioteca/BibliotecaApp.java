package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    private static ArrayList<Book> list = new ArrayList<Book>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        populateBookList();
        displayBookList();
    }

    public static void populateBookList(){
        list.add(new Book("First Book", "Billy", 1990));
        list.add(new Book("Second Book", "Bob", 1998));
        list.add(new Book("Third Book", "Joe", 1986));
    }

    public static void displayBookList(){
        for (Book i:list) {
            System.out.println(i.toString());
        }
    }
}

class Book{

    private String title;
    private String author;
    private int yearPulished;

    public Book(String t, String a, int i) {
        title = t;
        author = a;
        yearPulished = i;
    }

    @Override
    public String toString() {
        return title + " || " + author + " || " + yearPulished;
    }
}
