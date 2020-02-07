package com.twu.biblioteca;

import java.util.ArrayList;

class Customer{
    private String libraryNumber, password;
    private String name, email, phoneNumber;
    private ArrayList<Book> checkedOut = new ArrayList<Book>();

    public Customer(String libraryNumber, String password, String name, String email, String phoneNumber){
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

    public void checkOutBook(Book book){
        checkedOut.add(book);
    }

    @Override
    public String toString(){
        return name + " || " + email + " || " + phoneNumber;
    }

    public ArrayList<Book> getCheckOutBooks() {
        return checkedOut;
    }
}
