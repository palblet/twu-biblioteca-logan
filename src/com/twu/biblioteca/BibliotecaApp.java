package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> list = new ArrayList<Book>();

    public static void main(String[] args) {
        boolean done = false;

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        populateBookList();

        while(!done){

            displayMenu();

            switch(getMenuInput()){
                case 0: done = true; break;
                case 1: displayBookList(); break;
            }
        }
    }

    public static void displayMenu(){
        System.out.println("Menu:\t"+
                "1: List of Books");
    }

    public static int getMenuInput() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        do{
            System.out.println("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInput(input));
        return Integer.parseInt(input);
    }

    public static boolean checkInput(String input){
        boolean valid = true;
        int i = 0;
        if(input.isEmpty()){
            notValid();
        }else {
            try {
                i = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                notValid();
                valid = false;
            }
            if (i > 1 || i < 0) {
                notValid();
                valid = false;
            }
        }
        return valid;
    }

    public static void notValid(){
        System.out.println("input is not valid");
    }

    public static void populateBookList(){
        list.add(new Book("First Book", "Billy Bob", 1990));
        list.add(new Book("Second Book", "Bob Billy", 1998));
        list.add(new Book("Third Book", "Joe Bob", 2001));
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
