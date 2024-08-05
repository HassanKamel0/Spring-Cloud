package com.example.Master.Microservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties("id")
public class UserV2 {
    private Integer id;
    @JsonProperty("Name")
    private Name name;
    @JsonProperty("Age")
    private int age;
    @JsonIgnore
    private LocalDate birthDate;

    public UserV2(Integer id, Name name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
    }
}
