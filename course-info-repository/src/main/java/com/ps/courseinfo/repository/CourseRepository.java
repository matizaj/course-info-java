package com.ps.courseinfo.repository;

import com.ps.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void save(Course course);
    List<Course> getAll();

    static CourseRepository openCourseRepository(String dbFile) {
        return  new CourseJdbcRepository(dbFile);
    }

}
