package com.example.Master.Microservices.controller;

import com.example.Master.Microservices.entity.Post;
import com.example.Master.Microservices.entity.User;
import com.example.Master.Microservices.exceptionHandling.UserNotFoundException;
import com.example.Master.Microservices.repository.PostRepository;
import com.example.Master.Microservices.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor
@RestController
@RequestMapping("/jpa")
public class UserJpaController {
    private UserRepository userRepository;
    private PostRepository postRepository;
    @GetMapping("/v1/users")
    public List<User> retrieveUsersV1() {
        return userRepository.findAll();
    }
    @GetMapping("/v1/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
    }
    @GetMapping("/v1/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id) {
        User user= userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        return user.getPosts();
    }
    @PostMapping("/v1/users")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User newUser= userRepository.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/v1/users/{id}/posts")
    public ResponseEntity createUserPost(@PathVariable int id, @Valid @RequestBody Post post) {
        User user= userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        post.setUser(user);
        postRepository.save(post);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/v1/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
