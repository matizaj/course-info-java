package com.ps.courseinfo.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }
}
