package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {


        //Example 1
        //1- How we usually create an object
        //CalculatorService calculator = new CalculatorService();

        //Concept 1: How to get beans of spring
        //2- How we do it with spring, (spring gives it to us)
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CalculatorService service = (CalculatorService) context.getBean("calculatorService");
        String txt = service.heyWorld();
        System.out.println(txt);

        //Concept 2: How to load beans inside another bean
        //Example 2
        GestorFacturas gestor1 = (GestorFacturas) context.getBean("gestorFacturas");
        gestor1.sayHi();

        //Concept 3: scope
        //Can be singleton: one object for all application
        //prototype: one object each time
    }
}
