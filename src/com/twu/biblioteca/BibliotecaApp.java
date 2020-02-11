package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static List<Book> bookList = new ArrayList<Book>();
    private static List<Movie> movieList = new ArrayList<Movie>();
    private static List<User> users = new ArrayList<User>();
    private static int availableBooks, availableMovies;
    private static User currentUser;

    public static void main(String[] args) {
        t("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");

        populateUsers();
        populateBookList();
        populateMovieList();

        users.get(3).makeLibrarian();

        boolean done = false;

        loggedIn();

        int numMenuItems = 7;
        while(!done){

            displayMenu();

            switch(getInput(numMenuItems)){
                case 0: done = true;break;
                case 1: displayBookList();break;
                case 2: displayMovieList();break;
                case 3: checkOutBook();break;
                case 4: checkInBook();break;
                case 5: checkOutMovie();break;
                case 6: displayUserInfo();break;
                case 7:
                    if(currentUser.isLibrarian())
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
    public static boolean loggedIn(){
        t("libraryNumber: ");
        String libraryNumber = getInput();
        User attempt = validateLibraryNumber(libraryNumber);
        if(!attemptPassword(attempt))
            return loggedIn();
        return true;
    }

    //checks if log in user is correct
    public static User validateLibraryNumber(String libraryNumber) {
        for (User c: users) {
            if(c.getLibraryNumber().equals(libraryNumber)){
                currentUser = c;
                return c;
            }
        }
        return null;
    }

    //attempt password for the user
    public static boolean attemptPassword(User attempt){
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
                "6: Display User Info");
        if(currentUser.isLibrarian()){
            t("7: Who has books checked out");
        }
        t("0: Exit");
    }

    public static void displayUserInfo(){
        t(currentUser.toString());
    }

    public static void populateBookList(){
        bookList.add(new Book("First Book", "Billy Bob", 1990));
        bookList.add(new Book("Second Book", "Bob Billy", 1998));
        bookList.add(new Book("Third Book", "Joe Bob", 2001));
        availableBooks = bookList.size();
    }

    public static void populateMovieList() {
        movieList.add(new Movie("First Movie", 1987,
                "Some Guy", 6));
        movieList.add(new Movie("Second Movie", 1999,
                "His Brother", 8));
        movieList.add(new Movie("Third Movie", 2007,
                "Geff", 3));
        availableMovies = movieList.size();
    }

    public static void populateUsers() {
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
            if (i > range || i < 0) {
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
        if(i == -1)
            t("There is no book there");
        else if(bookList.get(i).isCheckedOut())
            t("Sorry, that book is not available");
        else{
            t("Thank you! Enjoy the book");
            bookList.get(i).checkOut();
            currentUser.checkOutBook(bookList.get(i));
            removeCheckedOutBook(i);
            availableBooks--;
        }
    }

    //Checks out a movie
    public static void checkOutMovie(){
        if(availableMovies != 0){
            t("There are " + availableMovies + " available.\n");
            int i = getInput(availableMovies) - 1;
            validateCheckOutMovie(i);
        }else
            t("There are no movies available.\n");

    }

    public static void validateCheckOutMovie(int i) {
        if(i == -1)
            t("There is no movie there");
        else if(movieList.get(i).isCheckedOut())
            t("Sorry, that movie is not available");
        else{
            t("Thank you! Enjoy the Movie");
            movieList.get(i).checkOut();
            removeCheckedOutMovie(i);
            availableMovies--;
        }
    }

    //Checks in a book
    public static void checkInBook() {
        t("Please give a Book title");
        String bookTitle;
        bookTitle = getInput();
        int bookLocation;
        bookLocation = findBook(bookTitle, bookList);
        int userBookLocation;
        userBookLocation = findBook(bookTitle, currentUser.getCheckOutBooks());
        validateCheckInBook(bookLocation, userBookLocation);
    }

    //Finds the book location by title
    public static int findBook(String bookTitle, List<Book> books){
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
            currentUser.checkInBook(bookList.get(bookLocation));
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