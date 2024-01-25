package com.amichdev.spring.dataaccess.controller;

import com.amichdev.spring.dataaccess.course.domain.Course;
import com.amichdev.spring.dataaccess.course.repository.CoursesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Courses", description = "Courses Api using Spring WebServices")
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    final private CoursesRepository coursesRepository;

    @GetMapping
    public List<Course> getAllCoursed() {
        return coursesRepository.findAll();
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getUserById(@PathVariable Long courseId) {
        Optional<Course> user = coursesRepository.findById(courseId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Course> createUser(@RequestBody Course course) {
        Course createdCourse = coursesRepository.save(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {

        coursesRepository.deleteById(courseId);
        updatedCourse.setId(courseId);
        Course course = coursesRepository.save(updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        coursesRepository.deleteById(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
