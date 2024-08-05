package com.example.Master.Microservices.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity(name = "user_details")
@Getter
@Setter
//@JsonFilter("Users filter")
public class User {
    protected User() {
    }
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, max = 20,message = "Name should be between 2 and 20 characters")
    private String name;

    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
