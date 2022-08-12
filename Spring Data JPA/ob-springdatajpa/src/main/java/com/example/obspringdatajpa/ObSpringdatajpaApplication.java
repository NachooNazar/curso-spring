package com.example.obspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringdatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObSpringdatajpaApplication.class, args);
		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println(repository.count());

		//Create and save a car in db
		Coche car = new Coche(null,2020,"Audi","A5");
		repository.save(car);

		//Get the car by id
		System.out.println(repository.findById(car.get_id()));
	}

}
