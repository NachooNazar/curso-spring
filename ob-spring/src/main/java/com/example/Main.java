package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        CalculatorService service = (CalculatorService) context.getBean("calculatorService");
        CalculatorService service2 = (CalculatorService) context.getBean("calculatorService");
        CalculatorService service3 = (CalculatorService) context.getBean("calculatorService");

        String txt = service.heyWorld();

        System.out.println(txt);

        String txt2 = service2.heyWorld();

        System.out.println(txt2);

        String txt3 = service3.heyWorld();

        System.out.println(txt3);
    }
}
