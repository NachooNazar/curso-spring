package com.rest.apirestspringboot.controller;

import com.rest.apirestspringboot.entities.Book;
import com.rest.apirestspringboot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;

    //CRUD ABOUT BOOK ENTITY
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //GET ALL
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Request
     * Response
     *
     * @param id
     * @return book
     */
    // GET BY ID
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

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
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        if (newBook.getId() != null) {
            log.warn("Book already exists: " + newBook.getId() + " Post Book Method");
            System.out.println("Book already exists: " + newBook.getId() + " Post Book Method");
            return ResponseEntity.badRequest().build();
        }
        Book res = bookRepository.save(newBook);
        return ResponseEntity.ok(res);
    }

    /**
     * Update book db
     *
     * @param book id
     * @return Book
     */
    @PutMapping("/api/book")
    public ResponseEntity<Book> updateBook(@RequestBody Book newBook) {

        if (newBook.getId() == null) {
            log.warn("Trying to update an empty book");
            return ResponseEntity.badRequest().build();
        }

        if (!bookRepository.existsById(newBook.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Book res = bookRepository.save(newBook);
        return ResponseEntity.ok(res);
    }
    //DELETE

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        try {
            bookRepository.deleteById(id);

        } catch (Exception e) {
            log.error("Error deleting book ", e);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/api/book")
    public ResponseEntity<Book> deleteAllBooks(){

        if(bookRepository.count() == 0){
            log.warn("No books to delete");
            return ResponseEntity.badRequest().build();
        }

        try{
            bookRepository.deleteAll();

        }catch(Exception e){
            log.error("Error deleting book ", e);
        }
        return ResponseEntity.noContent().build();
    }
}
