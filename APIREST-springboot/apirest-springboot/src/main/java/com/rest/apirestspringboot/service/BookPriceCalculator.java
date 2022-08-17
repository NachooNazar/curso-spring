package com.rest.apirestspringboot.service;

import com.rest.apirestspringboot.entities.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book){
         double price = book.getPrice();

         if(book.getPages() > 300){
             price += 5;
         }
         price += 1.21d;
         return price;
    }

    public String asd(){
        return "asd";
    }
}
