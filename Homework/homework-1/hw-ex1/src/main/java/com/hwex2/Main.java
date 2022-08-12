package com.hwex2;

import com.hwex1.Saludo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //How we do it with spring. get beans
        //We need the context first, classpathxmlapplicationcontext give it to us.
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //Next we need get the bean what we are going to use
        //We create a variable with the type of the bean
        UserService userServ = (UserService) context.getBean("userService");
        userServ.notification.notifier();

    }
}
