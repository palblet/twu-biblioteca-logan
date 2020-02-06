package com.twu.biblioteca;

import com.sun.codemodel.internal.JArray;

import java.util.ArrayList;

public class BibliotecaApp {
    static ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        populateBookList();
        displayBookList();

    }

    public static void populateBookList(){
        list.add("Book One");
        list.add("Book Two");
        list.add("Book Three");
    }

    public static void displayBookList(){
        for (String i:list) {
            System.out.println(i);
        }
    }
}
