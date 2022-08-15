package com.example.homework.controller;

import com.example.homework.entity.Laptop;
import com.example.homework.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> getAll(){
        return repository.findAll();
    }

    @PostMapping("/api/laptop")
    public Laptop createLaptop(@RequestBody Laptop l1){
        return repository.save(l1);
    }
}
