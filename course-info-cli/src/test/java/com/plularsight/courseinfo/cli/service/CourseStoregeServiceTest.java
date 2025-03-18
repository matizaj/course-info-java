package com.plularsight.courseinfo.cli.service;

import com.plularsight.courseinfo.domain.Course;
import com.plularsight.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseStoregeServiceTest {

    @Test
    void storeCourses() {
        var repo = new InMemoryCourseRepository();
        var storageService = new CourseStoregeService(repo);
    }

    private class InMemoryCourseRepository implements CourseRepository {

        @Override
        public void saveCourse(Course course) {

        }

        @Override
        public List<Course> gettAllCourses() {
            return null;
        }
    }
}