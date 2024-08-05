package com.example.Master.Microservices.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldBean {
    private String message;
    public HelloWorldBean(String message) {
        this.message=message;
    }
}
