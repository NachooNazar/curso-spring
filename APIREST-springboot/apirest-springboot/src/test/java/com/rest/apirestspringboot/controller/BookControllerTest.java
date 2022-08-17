package com.rest.apirestspringboot.controller;

import com.rest.apirestspringboot.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;
    private Book newbook = new Book(null, "asd", LocalDate.now(), "nacho", 500, 152d, true);
    @Autowired

    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int PORT;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + PORT);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Test hello world by spring rest controller")
    @Test
    void hello() {
        ResponseEntity<String> res = testRestTemplate.getForEntity("/hello", String.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> res = testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, res.getStatusCode());

        List<Book> list = Arrays.asList(Objects.requireNonNull(res.getBody()));
        System.out.println(list.size());

    }

    @Test
    void findById() {
        ResponseEntity<Book> res = testRestTemplate.getForEntity("/api/book/1", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

        List<Book> list = List.of(Objects.requireNonNull(res.getBody()));
        System.out.println(list.size());
    }

    @Test
    void createBook() {
        // crear cabezeras
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(List.of(MediaType.APPLICATION_JSON));

        // crear json
        String json = """
                {
                    "name":"Nashe",
                    "releaseDate": "2001-11-11",
                    "author": "Coscu",
                    "pages": 5000,
                    "price": 128.69,
                    "online": true
                }
                """;

        // crear request
        HttpEntity<String> request = new HttpEntity<>(json, header);

        ResponseEntity<Book> res = testRestTemplate.exchange("/api/book", HttpMethod.POST, request, Book.class);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals("Nashe", res.getBody().getName());
        System.out.println(res.getBody().getName());
    }
}