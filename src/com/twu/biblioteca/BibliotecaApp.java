package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<Movie> movieList = new ArrayList<Movie>();
    private static ArrayList<User> users = new ArrayList<User>();
    private static int availableBooks = 3, availableMovies = 3;
    private static User user;

    public static void main(String[] args) {
        t("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");

        populateUsers();
        populateBookList();
        populateMovieList();

        users.get(3).makeLibrarian();

        boolean loggedIn = false, done = false;

        while(!loggedIn){
            loggedIn = logIn();
        }

        int numMenuItems = 8;
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
                case 8:
                    if(user.isLibrarian())
                        displayCheckedOut();
                    break;
            }
        }
    }

    public static void displayCheckedOut() {
        for (User user:users) {
            t(user.getName() + " Has checked out:\n" +
                    user.checkedOutBooksString());
        }
    }

    //Starts log in
    public static boolean logIn(){
        t("libraryNumber: ");
        String libraryNumber = getInput();
        User attempt = validateLibraryNumber(libraryNumber);
        return attemptPassword(attempt);
    }

    //checks if log in user is correct
    private static User validateLibraryNumber(String libraryNumber) {
        for (User c: users) {
            if(c.getLibraryNumber().equals(libraryNumber)){
                user = c;
                return c;
            }
        }
        return null;
    }

    //attempt password for the user
    private static boolean attemptPassword(User attempt){
        if(attempt != null){
            t("Password: ");
            String password = getInput();
            return attempt.getPassword().equals(password);
        }
        return false;
    }

    public static void displayMenu(){
        t("Menu:\n"+
                "1: List of Books\n"+
                "2: List of Movies\n"+
                "3: Check out a Book\n"+
                "4: Check in a Book\n"+
                "5: Check out a Movie\n"+
                "6: Display User Info\n"+
                "7: Exit");
        if(user.isLibrarian()){
            t("8: Who has books checked out");
        }
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
        movieList.add(new Movie("First Movie", 1987,
                "Some Guy", 6));
        movieList.add(new Movie("Second Movie", 1999,
                "His Brother", 8));
        movieList.add(new Movie("Third Movie", 2007,
                "Geff", 3));
    }

    private static void populateUsers() {
        users.add(new User("123-4560","password",
                "Sam Pepper", "sp@yahoo.com", "1234567890"));
        users.add(new User("890-0980","password",
                "Lonely Heart", "lh@yahoo.com", "2140009999"));
        users.add(new User("654-3210","password",
                "Club Band", "cb@yahoo.com", "8005551234"));
        users.add(new User("123-1231","password",
                "Lib Rarian", "lr@library.net", "1112223333"));
    }

    //Displays books in the list
    public static void displayBookList(){
        int num = 0;
        for (Book i:bookList) {
            if(!i.isCheckedOut()) {
                t(++num + ": " + i.toString() + "\n");
            }
        }
    }

    //Displays movies in the list
    public static void displayMovieList(){
        int num = 0;
        for (Movie i:movieList) {
            if(!i.isCheckedOut()) {
                t(++num + ": " + i.toString() + "\n");
            }
        }
    }

    //Get input for a numbered list of choices
    public static int getInput(int num) {
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            input = scan.nextLine();
        }while(!checkInputRange(input, num));
        return Integer.parseInt(input);
    }

    //Get input for an open ended question
    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
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
                valid = notValid();
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
            valid = notValid();
        }
        return valid;
    }

    //Checking if the input isn't empty
    public static boolean checkInput(String input){
        boolean valid = true;
            if(input.isEmpty()){
                valid = notValid();
            }
        return valid;
    }

    //Checks out a book
    public static void checkOutBook(){
        if(availableBooks != 0){
            t("There are " + availableBooks + " available.\n");
            int i = getInput(availableBooks) - 1;
            validateCheckOutBook(i);
        }else
            t("There are no books available.\n");
    }

    public static void validateCheckOutBook(int i){
        if(bookList.get(i).isCheckedOut())
        {
            t("Sorry, that book is not available");
        }else{
            t("Thank you! Enjoy the book");
            bookList.get(i).checkOut();
            user.checkOutBook(bookList.get(i));
            removeCheckedOutBook(i);
            availableBooks--;
        }
    }

    //Checks out a movie
    public static void checkOutMovie(){
        if(availableMovies != 0){
            t("There are " + availableMovies + " available.\n");
            int i = getInput(availableMovies);
            validateCheckOutMovie(i);
        }else
            t("There are no movies available.\n");

    }

    private static void validateCheckOutMovie(int i) {
        if(movieList.get(i-1).isCheckedOut())
        {
            t("Sorry, that movie is not available");
        }else{
            t("Thank you! Enjoy the Movie");
            movieList.get(i-1).checkOut();
            removeCheckedOutMovie(i-1);
            availableMovies--;
        }
    }

    //Checks in a book
    private static void checkInBook() {
        t("Please give a Book title");
        String bookTitle;
        bookTitle = getInput();
        int bookLocation;
        bookLocation = findBook(bookTitle, bookList);
        int userBookLocation;
        userBookLocation = findBook(bookTitle, user.getCheckOutBooks());
        validateCheckInBook(bookLocation, userBookLocation);
    }

    //Finds the book location by title
    private static int findBook(String bookTitle, ArrayList<Book> books){
        int found = -1;
        for (Book book: books) {
            if(book.bookTitle().equals(bookTitle) && book.isCheckedOut()){
                found = books.indexOf(book);
            }
        }
        return found;
    }

    //If the checked out book location is real, check it in
    public static void validateCheckInBook(int bookLocation, int userBookLocation){
        if(bookLocation == -1) {
            t("That is not a valid book to return.");
        }else if (userBookLocation == -1){
            t("Someone else has that book checked out.");
        }else{
            t("Thank you for returning the book");
            user.checkInBook(bookList.get(bookLocation));
            bookList.get(bookLocation).checkIn();
            availableBooks++;
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
    public static boolean notValid(){
        t("input is not valid");
        return false;
    }

    //Quality of Life
    public static void t(String s){
        System.out.println(s);
    }
}