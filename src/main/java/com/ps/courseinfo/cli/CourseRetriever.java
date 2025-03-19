package com.ps.courseinfo.cli;

import com.ps.courseinfo.cli.services.Course;
import com.ps.courseinfo.cli.services.CourseRetrievalService;
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
        List<Course> coursesToStore = svc.getCoursesFor(authorId);
        LOG.info("Retrieved following {} courses {}",coursesToStore.size(), coursesToStore);

    }
}
