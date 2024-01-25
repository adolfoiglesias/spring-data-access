package com.amichdev.spring.dataaccess.course.repository;

import com.amichdev.spring.dataaccess.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {

}
