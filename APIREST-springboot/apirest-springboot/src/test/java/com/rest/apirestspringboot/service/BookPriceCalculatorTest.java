package com.rest.apirestspringboot.service;

import com.rest.apirestspringboot.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        //Config test
        Book book = new Book(null,"asd", LocalDate.now(),"nacho",500,152d,true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        //Execute
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        //Assertions
        assertTrue(price>0);
        assertEquals(158.21d,price);
    }
}