package com.plularsight.courseinfo.cli.service;

import com.plularsight.courseinfo.repository.CourseRepository;


import java.util.List;

public class CourseStoregeService {
    private final CourseRepository _repo;

    public CourseStoregeService(CourseRepository repo) {
        _repo = repo;
    }
    public void storeCourses(List<Course> courses) {
        for (var c: courses)
        _repo.saveCourse(new com.plularsight.courseinfo.domain.Course(
                c.id(),
                c.title(),
                c.duration(),
                c.contentUrl()));
    }
}
