package com.ps.courseinfo.cli.services;

import com.ps.courseinfo.domain.Course;
import com.ps.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {
    static class InMemoryCourseRepository implements CourseRepository {
        private final List<Course> courses = new ArrayList<>();
        @Override
        public void save(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAll() {
            return courses;
        }

        @Override
        public void addNote(String id, String note) {

        }
    }
    @Test
    void storePsCourses() {

        CourseRepository repository = new InMemoryCourseRepository();
        CourseStorageService svc = new CourseStorageService(repository);
        var psCourse = new PsCourse("1", "test ps course", "00:07:13", "/url-1", false);
        svc.storePsCourses(List.of(psCourse));

        Course expect = new Course("1", "test ps course",7, "/url-1", Optional.of("note1"));
        assertEquals(List.of(expect), repository.getAll());
    }
}