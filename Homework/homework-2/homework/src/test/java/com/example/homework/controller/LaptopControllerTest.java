package com.example.homework.controller;

import com.example.homework.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int PORT;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + PORT);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void getAll() {
        ResponseEntity<Laptop[]> res = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);
        assertEquals(HttpStatus.OK,res.getStatusCode());
    }

    @Test
    void getById() {
        ResponseEntity<Laptop> res = testRestTemplate.getForEntity("/api/laptops/1",Laptop.class);
        assertEquals(HttpStatus.UNAUTHORIZED,res.getStatusCode());
    }

    @Test
    void updateById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json =
                """
                                    {
                                    "id":"1",
                                    "brand": "amd",
                                    "name": "nashex2"
                                  }
                        """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);


        ResponseEntity<Laptop> res = testRestTemplate.exchange("/api/laptops/1", HttpMethod.PUT,request, Laptop.class);

        assertEquals(HttpStatus.UNAUTHORIZED, res.getStatusCode());
    }

    @Test
    void deleteById() {
       testRestTemplate.delete("/api/laptops/1");
    }

    @Test
    void deleteAll() {
        testRestTemplate.delete("/api/laptops");
        getAll();
    }

    @Test
    void createLaptop() {

        //crear cabeceras
        //HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        //creat json con """  """
        String json =
                """
                                    {
                                    "brand": "amd",
                                    "name": "nashex2"
                                  }
                        """;
        //crear request
        //HttpEntity
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> res = testRestTemplate.exchange("/api/laptop", HttpMethod.POST,request,Laptop.class);
        assertEquals(HttpStatus.UNAUTHORIZED, res.getStatusCode());
    }
}