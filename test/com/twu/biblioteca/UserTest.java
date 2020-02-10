package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    Book testBook = new Book("title", "author", 1900);
    User testUser = new User("123","password","name","email@email.com","123");


    @Test
    public void hasCheckedOutBooks() {
        testBook.checkOut();
        testUser.checkOutBook(testBook);
        assertEquals(1, testUser.getCheckOutBooks().size());
    }

    @Test
    public void userIsLibrarian(){
        testUser.makeLibrarian();
        assertTrue(testUser.isLibrarian());
    }

    @Test
    public void userHasBook(){
        testUser.checkOutBook(testBook);
        assertTrue(testUser.hasBook(testBook.bookTitle()));
    }

    @Test
    public void bookWasRemoved(){
        testUser.checkOutBook(testBook);
        testUser.checkInBook(testBook);
        assertTrue(!testUser.hasBook("title"));
    }

}
