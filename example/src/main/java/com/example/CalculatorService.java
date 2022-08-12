package com.example;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    public CalculatorService() {
        System.out.println("Ejecutando constructor calculatorService");
    }

    public String heyWorld(){
        return "Hello World!";
    }
}
