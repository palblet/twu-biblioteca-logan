package com.twu.biblioteca;


import org.junit.Test;

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

    @Test
    public void userIsLibrarian(){
        User testUser = new User("123","password","name","email@email.com","123");
        testUser.makeLibrarian();
        assertTrue(testUser.librarian);
    }

}
