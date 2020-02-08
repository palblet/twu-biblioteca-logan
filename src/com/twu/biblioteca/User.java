package com.twu.biblioteca;

import java.util.ArrayList;

class User {
    private String libraryNumber, password;
    private String name, email, phoneNumber;
    private ArrayList<Book> checkedOut = new ArrayList<Book>();
    boolean librarian = false;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber){
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getlibraryNumber(){
        return libraryNumber;
    }

    public String getPassword(){
        return password;
    }

    public String getName() {
        return name;
    }

    public void checkOutBook(Book book){
        checkedOut.add(book);
    }
    public ArrayList<Book> getCheckOutBooks() {
        return checkedOut;
    }

    public boolean isLibrarian(){
        return librarian;
    }

    public void makeLibrarian(){
        librarian = true;
    }

    public String checkedOutBooksString(){
        String output = "";
        for (Book book:checkedOut) {
            output += book.toString();
        }
        return output;
    }

    @Override
    public String toString(){
        return name + " || " + email + " || " + phoneNumber;
    }

}