package com.example.homework;

import com.example.homework.entity.Laptop;
import com.example.homework.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);

		LaptopRepository repository = (LaptopRepository) context.getBean(LaptopRepository.class);

		Laptop lap1 = new Laptop(null,"HP OMEN","HP");
		Laptop lap2= new Laptop(null,"HP OMEN v2","HP");

		repository.save(lap1);
		repository.save(lap2);

		List<Laptop> list = repository.findAll();
		System.out.println("laps " + list.size());

		for(Laptop l : list) {
			System.out.println(l);
		}
	}


}
