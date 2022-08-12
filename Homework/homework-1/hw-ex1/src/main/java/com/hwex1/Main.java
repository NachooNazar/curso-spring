package com.hwex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        //How we do it with spring. get beans
        //We need the context first, classpathxmlapplicationcontext give it to us.
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //Next we need get the bean what we are going to use
        //We create a variable with the type of the bean
        Saludo s1 = (Saludo) context.getBean("printHi");
        String txt = s1.printHi();
        System.out.println(txt);
    }
}
