package com.example.learn_jpa_and_hibernate;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CourseSpringJpaRepository extends JpaRepository<Course, Integer> {
    List<Course> findByAuthor(String author);
    List<Course> findByName(String name);
}
