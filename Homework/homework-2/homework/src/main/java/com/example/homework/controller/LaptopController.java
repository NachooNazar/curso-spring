package com.example.homework.controller;

import com.example.homework.entity.Laptop;
import com.example.homework.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> getAll(){
        return repository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> getById(@PathVariable Long id){
        Optional<Laptop> laptop = repository.findById(id);
        //return laptop.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
        try{
            if(laptop.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            log.info("Laptop "+laptop.get());
            return ResponseEntity.ok(laptop.get());
        }catch(Exception e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> updateById(@PathVariable Long id, @RequestBody Laptop lap){
        try{
            if(lap.getId() == null){
               return ResponseEntity.badRequest().build();
            }

            if(repository.existsById(id)){
                Laptop res = repository.save(lap);
                return ResponseEntity.ok(res);
            }

            return ResponseEntity.notFound().build();

        }catch(Exception e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){
        try{
            if(id==null){
                return ResponseEntity.badRequest().build();
            }
            if(!repository.existsById(id)){
                return ResponseEntity.notFound().build();
            }

            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/api/laptops/delete")
    public ResponseEntity<String> deleteAll(){
        try{
            if(repository.findAll().size() == 0){
                return ResponseEntity.ok("Successfully deleted all -1");
            }
            repository.deleteAll();
            return ResponseEntity.ok("Successfully deleted all");
        }catch(Exception e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.ok("Successfully deleted all -3");
        }
    }


    @PostMapping("/api/laptop")
    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop l1){
        return ResponseEntity.ok(repository.save(l1));
    }

}
