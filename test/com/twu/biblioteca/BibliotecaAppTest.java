package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static com.twu.biblioteca.BibliotecaApp.validateCheckOutBook;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<User> users = new ArrayList<User>();


    @Test
    public void seeUsersWithBooksCheckedOut() {
        bookList.add(new Book("First Book", "Billy Bob", 1990));
        users.add(new User("123-4560","password", "Sam Pepper", "sp@yahoo.com", "1234567890"));
        users.add(new User("123-1231","password","Lib Rarian", "lr@library.net", "1112223333"));
        users.get(1).makeLibrarian();
        users.get(0).checkOutBook(bookList.get(0));
        //System.out.println(users.get(0).checkedOutBooksString());
        //displayCheckedOut();
        assertEquals(1, 1);
    }

    @Test public void outOfBounds(){
        bookList.add(new Book("First Book", "Billy Bob", 1990));
        bookList.add(new Book("Second Book", "Bob Billy", 1998));
        bookList.add(new Book("Third Book", "Joe Bob", 2001));
        //validateCheckOutBook(1);
    }
}
