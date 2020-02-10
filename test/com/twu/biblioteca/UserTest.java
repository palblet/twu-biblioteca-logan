package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void hasCheckedOutBooks() {
        Book testBook = new Book("title", "author", 1900);
        testBook.checkOut();
        User testUser = new User("123","password","name","email@email.com","123");
        testUser.checkOutBook(testBook);
        assertEquals(1, testUser.getCheckOutBooks().size());
    }


    User testUser = new User("123","password","name","email@email.com","123");

    @Test
    public void userIsLibrarian(){
        testUser.makeLibrarian();
        assertTrue(testUser.isLibrarian());
    }

    @Test
    public void userHasBook(){
        Book book =new Book("First Book", "Billy Bob", 1990);
        testUser.checkOutBook(book);
        assertTrue(testUser.hasBook(book.bookTitle()));
    }

}
