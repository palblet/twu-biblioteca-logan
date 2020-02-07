package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> list = new ArrayList<Book>();
    private static int numMenuItems = 3;
    private static int numOfBooks = 3;

    public static void main(String[] args) {
        boolean done = false;

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        space();

        populateBookList();

        while(!done){

            displayMenu();
            space();

            switch(getInput(numMenuItems)){
                case 3: done = true; break;
                case 1: displayBookList(); break;
                case 2: checkOutBook();break;
            }
        }
    }

    public static void displayMenu(){
        System.out.println("Menu:\n"+
                "1: List of Books\n"+
                "2: Check out a Book\n"+
                "3: Exit");
    }

    public static int getInput(int num) {
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.println("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInputRange(input, num));
        return Integer.parseInt(input);
    }

    public static boolean checkInputRange(String input, int range){
        boolean valid = checkInput(input);
        if(valid){
            int i = Integer.parseInt(input);
            if (i > range || i <= 0) {
                notValid();
                valid = false;
            }
        }
        return valid;
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
        int num = numOfBooks;
        for (Book i:list) {
            if(!i.isCheckedOut()) {
                System.out.println(numOfBooks - --num + ": " + i.toString());
            }
        }
        space();
    }

    public static void checkOutBook(){
        System.out.println("There are " + numOfBooks + " available.\n"+
                "please enter a book number.\n");
        int i = getInput(numOfBooks);
        if(list.get(i-1).isCheckedOut())
        {
            System.out.println("This book is already checked out");
        }else{
            System.out.println("Checking out " + list.get(i-1));
            list.get(i-1).checkOutBook();
            fixList(i-1);
        }
    }

    public static void fixList(int i){
        Book temp = list.get(i);
        list.remove(i);
        list.add(temp);
    }

    public static void space(){
        System.out.println();
    }
}

class Book{

    private String title;
    private String author;
    private int yearPublished;
    private boolean checkedOut = false;

    public Book(String t, String a, int i) {
        title = t;
        author = a;
        yearPublished = i;
    }

    public void checkOutBook(){
        checkedOut = true;
    }

    public boolean isCheckedOut(){
        return checkedOut;
    }

    @Override
    public String toString() {
        return title + " || " + author + " || " + yearPublished;
    }
}
