package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestorFacturas {

    //1. Atributes

    private CalculatorService calculator;
    private String name;
    //2. Constructors
    public GestorFacturas(CalculatorService calculator, String name) {
        System.out.println("Ejecutando constructor de facturas");
        this.calculator = calculator;
        this.name = name;
    }

    @Autowired
    public GestorFacturas(CalculatorService calculator) {
        this.calculator = calculator;
    }

    //3. methods
    public void sayHi(){
        System.out.println("Hi!");
    }
}
