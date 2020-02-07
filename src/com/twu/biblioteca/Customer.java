package com.twu.biblioteca;

import java.util.ArrayList;

class Customer{
    private String libraryNumber, password;
    private String name, email, phoneNumber;
    private ArrayList<Book> checkedOut = new ArrayList<Book>();

    public Customer(String l, String p, String n, String e, String pn){
        libraryNumber = l;
        password = p;
        name = n;
        email = e;
        phoneNumber = pn;
    }

    public String getlibraryNumber(){
        return libraryNumber;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return name + " || " + email + " || " + phoneNumber;
    }
}
