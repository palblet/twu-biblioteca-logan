package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> list = new ArrayList<Book>();
    private static int numMenuItems = 4;
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
                case 4: done = true;break;
                case 1: displayBookList();break;
                case 2: checkOutBook();break;
                case 3: checkInBook();break;
            }

            space();
        }
    }

    public static void displayMenu(){
        System.out.println("Menu:\n"+
                "1: List of Books\n"+
                "2: Check out a Book\n"+
                "3: Check in a Book\n"+
                "4: Exit");
    }

    public static int getInput(int num) {
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.print("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInputRange(input, num));
        return Integer.parseInt(input);
    }

    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.print("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInput(input));
        return input;
    }

    public static boolean checkInputRange(String input, int range){
        boolean valid = checkIntInput(input);
        if(valid){
            int i = Integer.parseInt(input);
            if (i > range || i <= 0) {
                notValid(valid);
            }
        }
        return valid;
    }

    public static boolean checkIntInput(String input){
        boolean valid = true;
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            notValid(valid);
        }
        return valid;
    }

    public static boolean checkInput(String input){
        boolean valid = true;
            if(input.isEmpty()){
                notValid(valid);
            }
        return valid;
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
        System.out.println("There are " + numOfBooks + " available.\n");
        int i = getInput(numOfBooks);
        if(list.get(i-1).isCheckedOut())
        {
            System.out.println("Sorry, that book is not available");
        }else{
            System.out.println("Thank you! Enjoy the book");
            list.get(i-1).checkOutBook();
            fixList(i-1);
        }
    }

    private static void checkInBook() {
        System.out.println("Please give a Book title");
        String book;
        int bookLocation;
        book = getInput();
        bookLocation = findBook(book);
        validateCheckIn(bookLocation);
    }

    private static int findBook(String book){
        int found = -1;
        for (Book i: list) {
            if(i.isCheckedOut() && i.bookTitle().equals(book)){
                found = list.indexOf(i);
            }
        }
        return found;
    }

    public static void validateCheckIn(int bookLocation){
        if(bookLocation == -1){
            System.out.println("That is not a valid book to return.");
        }else{
            System.out.println("Thank you for returning the book");
            list.get(bookLocation).checkInBook();
        }
    }

    public static void fixList(int i){
        Book temp = list.get(i);
        list.remove(i);
        list.add(temp);
    }

    public static boolean notValid(boolean valid){
        System.out.println("input is not valid");
        valid = !valid;
        return valid;
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

    public String bookTitle(){
        return title;
    }

    public void checkOutBook(){
        checkedOut = true;
    }

    public void checkInBook(){
        checkedOut = false;
    }

    public boolean isCheckedOut(){
        return checkedOut;
    }

    @Override
    public String toString() {
        return title + " || " + author + " || " + yearPublished;
    }
}