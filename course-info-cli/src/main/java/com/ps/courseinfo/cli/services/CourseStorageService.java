package com.ps.courseinfo.cli.services;

import com.ps.courseinfo.repository.CourseRepository;
import com.ps.courseinfo.domain.Course;

import java.util.List;

public class CourseStorageService {
    private final CourseRepository repo;
    public CourseStorageService(CourseRepository repo) {
        this.repo = repo;
    }

    public void storePsCourses(List<PsCourse> psCourses) {
        for (var psCourse: psCourses) {
            Course c = new Course(psCourse.id(), psCourse.title(), psCourse.durationInMinutes(), psCourse.contentUrl());
            repo.save(c);
        }
    }
}
