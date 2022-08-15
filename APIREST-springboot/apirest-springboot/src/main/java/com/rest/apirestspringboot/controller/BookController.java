package com.rest.apirestspringboot.controller;

import com.rest.apirestspringboot.entities.Book;
import com.rest.apirestspringboot.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository bookRepository;
    //CRUD ABOUT BOOK ENTITY
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    //GET ALL
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    /**
    * Request
    * Response
    * @param id
    * @return book
     * */
    // GET BY ID
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){

        Optional<Book> bookopt = bookRepository.findById(id);
        /*
        * if(bookopt.isPresent()){
            return ResponseEntity.ok(bookopt.get());
        }else{
            return ResponseEntity.notFound().build();
       }*/

        //lambda function equals to arrow function
        //Class name :: callback name
        return bookopt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //Are the same
        //return bookopt.orElse(null);

    }
    //CREATE

    @PostMapping("/api/book")
    public Book createBook(@RequestBody Book newBook){
        return bookRepository.save(newBook);
    }
    //UPDATE
    @PutMapping("/api/book/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Long id){
        bookRepository.flush();
    }
    //DELETE
}
