package com.example.learn_jpa_and_hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void insert(Course course) {
        entityManager.merge(course);
    }
    public Course select(long id) {
        return entityManager.find(Course.class, id);
    }
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }
}
