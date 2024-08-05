package com.example.Master.Microservices.controller;

import com.example.Master.Microservices.entity.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World, "+name);
    }
    @GetMapping("/hello-world-internationalization")
    public String helloWorldInternationalization() {
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null, "Default Message",locale);
    }
}
