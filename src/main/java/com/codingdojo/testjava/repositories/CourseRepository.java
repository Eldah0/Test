package com.codingdojo.testjava.repositories;

import com.codingdojo.testjava.models.Course;
import com.codingdojo.testjava.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
    Course findByIdIs(Long id);
    List<Course> findAllByUser(User user);
    List<Course> findByUserNotContains(User user);


}