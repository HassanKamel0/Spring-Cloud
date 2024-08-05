package com.example.Master.Microservices.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Master.Microservices.Dao.UserDaoService;
import com.example.Master.Microservices.entity.User;
import com.example.Master.Microservices.entity.UserV2;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }
    @GetMapping("/v1/users")
    public List<User> retrieveUsersV1() {
        return service.getUsers();
    }
    @GetMapping("/v2/users")
    public List<UserV2> retrieveUsersV2() {
        return service.getUsersV2();
    }
    @GetMapping("/v3/users")
    public MappingJacksonValue retrieveUsersFilter() {
        MappingJacksonValue mapping = new MappingJacksonValue(service.getUsers());
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("name","birthDate");
        FilterProvider filters=new SimpleFilterProvider().addFilter("Users filter", filter);
        mapping.setFilters(filters);
        return mapping;
    }
    @GetMapping("/users")
    public List retrieveUsersVersionFromRequestParam(@RequestParam int version) {
        return version==1?service.getUsers():service.getUsersV2();
    }
    @GetMapping("/users/header")
    public List retrieveUsersVersionFromHeader(@RequestHeader int version) {
        return version==1?service.getUsers():service.getUsersV2();
    }
    @GetMapping(path = "/users",produces = "application/vnd.company.app-v1+json")
    public List retrieveUsersMediaTypeV1() {
        return service.getUsers();
    }
    @GetMapping(path = "/users",produces = "application/vnd.company.app-v2+json")
    public List retrieveUsersMediaTypeV2() {
        return service.getUsersV2();
    }
    @GetMapping("/v1/users/hateoas/{id}")
    public EntityModel<User> retrieveUserHATEOAS(@PathVariable int id) {
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveUsersV1());
        return EntityModel.of(service.getUser(id)).add(link.withRel("all-users"));
    }
    @GetMapping("/v1/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.getUser(id);
    }
    @PostMapping("/v1/users")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User newUser=service.saveUser(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/v1/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
