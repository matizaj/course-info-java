package com.plularsight.courseinfo.cli;

import com.plularsight.courseinfo.cli.service.Course;
import com.plularsight.courseinfo.cli.service.CourseRetrievalService;
import com.plularsight.courseinfo.cli.service.CourseStoregeService;
import com.plularsight.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String... args){
        LOG.info("CourseRetriever started!");
        if(args.length == 0) {
           LOG.warn("Please provide an author name as first argument");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch (Exception ex) {
            LOG.error("Unexpected error", ex);
        }
    }

    private static void retrieveCourses(String authorId) {
       LOG.info("Retriving courses for author '{}'", authorId);
       var courseRepository = CourseRepository.openCourseRepository("./courses.db");
       var courseStorageService = new CourseStoregeService(courseRepository);
        var courseRetrievalService = new CourseRetrievalService();
        List<Course> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(x -> !x.isRetired())
                .toList();

        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
        courseStorageService.storeCourses(coursesToStore);
        LOG.info("Courses are stored!");

    }
}
