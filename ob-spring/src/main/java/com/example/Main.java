package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        //Example 1
        //1- How we usually create an object
        CalculatorService calculator = new CalculatorService();

        //2- How we do it with spring, (spring gives it to us)
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CalculatorService service = (CalculatorService) context.getBean("calculatorService");
        String txt = service.heyWorld();
        System.out.println(txt);

        //Example 2
        GestorFacturas gestor1 = (GestorFacturas) context.getBean("gestorFacturas");
        gestor1.sayHi();
    }
}
