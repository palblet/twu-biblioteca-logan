package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<Movie> movieList = new ArrayList<Movie>();
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static int numMenuItems = 7, numOfBooks = 3, numOfMovies = 3;
    private static Customer user;

    public static void main(String[] args) {
        t("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");

        populateBookList();
        populateMovieList();
        populateCustomers();

        boolean loggedIn = false, done = false;

        while(!loggedIn){
            loggedIn = logIn();
        }

        while(!done){

            displayMenu();

            switch(getInput(numMenuItems)){
                case 7: done = true;break;
                case 1: displayBookList();break;
                case 2: displayMovieList();break;
                case 3: checkOutBook();break;
                case 4: checkInBook();break;
                case 5: checkOutMovie();break;
                case 6: displayUserInfo();break;
            }
        }
    }

    public static boolean logIn(){
        boolean loggedIn = false;
        t("Username: ");
        return loggedIn;
    }

    public static void displayMenu(){
        t("Menu:\n"+
                "1: List of Books\n"+
                "2: List of Movies\n"+
                "3: Check out a Book\n"+
                "4: Check in a Book\n"+
                "5: Check out a Movie\n"+
                "6: Exit\n");
    }

    public static void displayUserInfo(){
        t(user.toString());
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

    private static void populateCustomers() {
        customers.add(new Customer("123-456","password", "Sam Pepper", "sp@yahoo.com", "1234567890"));
        customers.add(new Customer("890-098","password", "Lonely Heart", "lh@yahoo.com", "2140009999"));
        customers.add(new Customer("654-321","password", "Cub Band", "cb@yahoo.com", "8005551234"));
    }

    //Displays books in the list
    public static void displayBookList(){
        int num = numOfBooks;
        for (Book i:bookList) {
            if(!i.isCheckedOut()) {
                t(numOfBooks - --num + ": " + i.toString() + "\n");
            }
        }
    }

    //Displays movies in the list
    public static void displayMovieList(){
        int num = numOfMovies;
        for (Movie i:movieList) {
            if(!i.isCheckedOut()) {
                t(numOfMovies - --num + " : " + i.toString() + "\n");
            }
        }
    }

    //Get input for a numbered list of choices
    public static int getInput(int num) {
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            t("Please enter selection: ");
            input = scan.nextLine();
        }while(!checkInputRange(input, num));
        return Integer.parseInt(input);
    }

    //Get input for an open ended question
    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            t("Please enter selection: ");
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
        t("There are " + numOfBooks + " available.\n");
        int i = getInput(numOfBooks);
        validateCheckOutBook(i);
    }

    public static void validateCheckOutBook(int i){
        if(bookList.get(i-1).isCheckedOut())
        {
            t("Sorry, that book is not available");
        }else{
            t("Thank you! Enjoy the book");
            bookList.get(i-1).checkOut();
            removeCheckedOutBook(i-1);
        }
    }

    //Checks out a movie
    public static void checkOutMovie(){
        t("There are " + numOfMovies + "available.\n");
        int i = getInput(numOfMovies);
        validateCheckOutMovie(i);
    }

    private static void validateCheckOutMovie(int i) {
        if(movieList.get(i-1).isCheckedOut())
        {
            t("Sorry, that movie is not available");
        }else{
            t("Thank you! Enjoy the Movie");
            movieList.get(i-1).checkOut();
            removeCheckedOutMovie(i-1);
        }
    }

    //Checks in a book
    private static void checkInBook() {
        t("Please give a Book title");
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
            t("That is not a valid book to return.");
        }else{
            t("Thank you for returning the book");
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
        t("input is not valid");
        valid = !valid;
        return valid;
    }

    //Quality of Life
    public static void t(String s){
        System.out.println(s);
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

class Customer{
    private String libraryNumber, password;
    private String name, email, phoneNumber;

    public Customer(String l, String p, String n, String e, String pn){
        libraryNumber = l;
        password = p;
        name = n;
        email = e;
        phoneNumber = pn;
    }

    @Override
    public String toString(){
        return name + " || " + email + " || " + phoneNumber;
    }
}