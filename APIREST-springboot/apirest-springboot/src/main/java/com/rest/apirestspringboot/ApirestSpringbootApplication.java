package com.rest.apirestspringboot;

import com.rest.apirestspringboot.entities.Book;
import com.rest.apirestspringboot.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ApirestSpringbootApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ApirestSpringbootApplication.class, args);
		BookRepository repository = (BookRepository) context.getBean(BookRepository.class);

		//CRUD - BEFORE CONTROLLER CONCEPT
		//CREATE BOOK
		Book book = new Book(null,"Minecraft", LocalDate.of(2001,11,5),"Ignacio Nazar",500,125.69,true);
		Book book2 = new Book(null,"EliteCraft", LocalDate.of(2001,11,11),"Valentino D'Antonio",5000,128.69,true);

		//Save book
		repository.save(book);
		repository.save(book2);

		//get books
		List<Book> listOfBooks = repository.findAll();
		System.out.println("Books in db " + listOfBooks.size());

		//Delete book
		//repository.deleteById(1L);

		System.out.println("Books in db " + repository.findAll().size());
	}

}
