package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<Movie> movieList = new ArrayList<Movie>();
    private static int numMenuItems = 7, numOfBooks = 3, numOfMovies = 3;

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        space();

        populateBookList();
        populateMovieList();

        boolean done = false;
        while(!done){

            displayMenu();
            space();

            switch(getInput(numMenuItems)){
                case 7: done = true;break;
                case 1: displayBookList();break;
                case 2: displayMovieList();break;
                case 3: checkOutBook();break;
                case 4: checkInBook();break;
                case 5: checkOutMovie();break;
                //case 6: checkInMovie();break;
            }
            space();
        }
    }

    public static void displayMenu(){
        System.out.println("Menu:\n"+
                "1: List of Books\n"+
                "2: List of Movies\n"+
                "3: Check out a Book\n"+
                "4: Check in a Book\n"+
                "5: Check out a Movie\n"+
                "6: Check in a Movie\n"+
                "7: Exit");
    }

    public static void populateBookList(){
        bookList.add(new Book("First Book", "Billy Bob", 1990));
        bookList.add(new Book("Second Book", "Bob Billy", 1998));
        bookList.add(new Book("Third Book", "Joe Bob", 2001));
    }

    private static void populateMovieList() {
        movieList.add(new Movie("First Movie", 1987, "Some Guy", 6));
        movieList.add(new Movie("Second Movie", 1999, "His Brother", 8));
        movieList.add(new Movie("Third Movie", 2007, "Geff", 3));
    }

    //Displays books in the list
    public static void displayBookList(){
        int num = numOfBooks;
        for (Book i:bookList) {
            if(!i.isCheckedOut()) {
                System.out.println(numOfBooks - --num + ": " + i.toString());
            }
        }
        space();
    }

    //Displays movies in the list
    public static void displayMovieList(){
        int num = numOfMovies;
        for (Movie i:movieList) {
            if(!i.isCheckedOut()) {
                System.out.println(numOfMovies - --num + " : " + i.toString());
            }
        }
        space();
    }

    //Get input for a numbered list of choices
    public static int getInput(int num) {
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.print("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInputRange(input, num));
        return Integer.parseInt(input);
    }

    //Get input for an open ended question
    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.print("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInput(input));
        return input;
    }

    //Checking the range for a number choice
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

    //Checking if the input for a number is a number
    public static boolean checkIntInput(String input){
        boolean valid = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            notValid(valid);
        }
        return valid;
    }

    //Checking if the input isn't empty
    public static boolean checkInput(String input){
        boolean valid = true;
            if(input.isEmpty()){
                notValid(valid);
            }
        return valid;
    }

    //Checks out a book
    public static void checkOutBook(){
        System.out.println("There are " + numOfBooks + " available.\n");
        int i = getInput(numOfBooks);
        validateCheckOutBook(i);
    }

    public static void validateCheckOutBook(int i){
        if(bookList.get(i-1).isCheckedOut())
        {
            System.out.println("Sorry, that book is not available");
        }else{
            System.out.println("Thank you! Enjoy the book");
            bookList.get(i-1).checkOut();
            removeCheckedOutBook(i-1);
        }
    }

    //Checks out a movie
    public static void checkOutMovie(){
        System.out.println("There are " + numOfMovies + "available.\n");
        int i = getInput(numOfMovies);
        validateCheckOutMovie(i);
    }

    private static void validateCheckOutMovie(int i) {
        if(movieList.get(i-1).isCheckedOut())
        {
            System.out.println("Sorry, that book is not available");
        }else{
            System.out.println("Thank you! Enjoy the book");
            movieList.get(i-1).checkOut();
            removeCheckedOutMovie(i-1);
        }
    }

    //Checks in a book
    private static void checkInBook() {
        System.out.println("Please give a Book title");
        String book;
        int bookLocation;
        book = getInput();
        bookLocation = findBook(book);
        validateCheckInBook(bookLocation);
    }

    //Finds the book location by title
    private static int findBook(String book){
        int found = -1;
        for (Book i: bookList) {
            if(i.isCheckedOut() && i.bookTitle().equals(book)){
                found = bookList.indexOf(i);
            }
        }
        return found;
    }

    //If the checked out book location is real, check it in
    public static void validateCheckInBook(int bookLocation){
        if(bookLocation == -1){
            System.out.println("That is not a valid book to return.");
        }else{
            System.out.println("Thank you for returning the book");
            bookList.get(bookLocation).checkIn();
        }
    }

    //Removes a checked out book from the displayed list of books
    public static void removeCheckedOutBook(int i){
        Book temp = bookList.get(i);
        bookList.remove(i);
        bookList.add(temp);
    }

    //Removes a checked out movie from the displayed list of movies
    public static void removeCheckedOutMovie(int i){
        Movie temp = movieList.get(i);
        movieList.remove(i);
        movieList.add(temp);
    }

    //Supplement method for validating input
    public static boolean notValid(boolean valid){
        System.out.println("input is not valid");
        valid = !valid;
        return valid;
    }

    //Quality of Life
    public static void space(){
        System.out.println();
    }
}

class libraryObject{

    private boolean checkedOut = false;

    public void checkOut(){
        checkedOut = true;
    }

    public void checkIn(){
        checkedOut = false;
    }

    public boolean isCheckedOut(){
        return checkedOut;
    }

}

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