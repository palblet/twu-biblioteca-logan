package com.twu.biblioteca;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void hasCheckedOutBooks() {
        Book testBook = new Book("title", "author", 1900);
        testBook.checkOut();
        Customer testCustomer = new Customer("123","password","name","email@email.com","123");
        testCustomer.checkOutBook(testBook);
        assertEquals(1, testCustomer.getCheckOutBooks().size());
    }

}
