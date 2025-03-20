package com.ps.courseinfo.cli;

import com.ps.courseinfo.cli.services.PsCourse;
import com.ps.courseinfo.cli.services.CourseRetrievalService;
import com.ps.courseinfo.cli.services.CourseStorageService;
import com.ps.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("CourseRetriever Started!");
        if(args.length ==0) {
            LOG.warn("Please provide an author name as firts argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch(Exception e) {
            LOG.error("Unexpected error: " + e);
        }

    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author: '{}'", authorId);
        CourseRetrievalService svc = new CourseRetrievalService();
        List<PsCourse> coursesToStore = svc.getCoursesFor(authorId);
        LOG.info("Retrieved following {} courses {}",coursesToStore.size(), coursesToStore);
        CourseRepository repo = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService storageSvc = new CourseStorageService(repo);

        var activeCourses = coursesToStore.stream().filter(x -> !x.isRetired()).toList();
        LOG.info("Retrieved following {} courses {}",activeCourses.size(), activeCourses);
        storageSvc.storePsCourses(activeCourses);
        LOG.info("Courses are successfully stored");

    }
}
