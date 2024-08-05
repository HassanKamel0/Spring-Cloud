package com.example.learn_jpa_and_hibernate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseJdbcRepository {
    private final JdbcTemplate springJdbcTemplete;
    private static String SELECT_QUERY = "select * from course where id = ?";
    private static String DELETE_QUERY = "delete course where id = ?";
    private static String INSERT_QUERY = "insert into course values (?,?,?)";

    public void deleteById(long id) {
        springJdbcTemplete.update(DELETE_QUERY,id);
    }
    public void insert(Course course) {
        springJdbcTemplete.update(INSERT_QUERY,course.getId(),course.getName(),course.getAuthor());
    }
    public Course select(long id) {
        return springJdbcTemplete.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class),id);
    }
}
