package com.example.learn_jpa_and_hibernate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseCommandLineRunner implements CommandLineRunner {
    //private final CourseJdbcRepository repository;
//    private final CourseJpaRepository repository;
    private final CourseSpringJpaRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "AWS", "i28mintues"));
        repository.save(new Course(2, "GCP", "i28mintues"));
        repository.save(new Course(3, "Azure", "i28mintues"));
        repository.deleteById(1);
        System.out.println(repository.findById(2));
        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("i28mintues"));
        System.out.println(repository.findByName("Azure"));
    }
}
