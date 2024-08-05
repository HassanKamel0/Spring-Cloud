package com.example.Master.Microservices.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Name {
    @Size(min = 2, max = 20,message = "First name should be between 2 and 20 characters")
    @JsonProperty("First Name")
    private String firstName;
    @Size(min = 2, max = 20,message = "Second name should be between 2 and 20 characters")
    @JsonProperty("Last Name")
    private String lastName;
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
