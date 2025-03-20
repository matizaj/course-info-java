package com.ps.courseinfo.server;

import com.ps.courseinfo.domain.Course;
import com.ps.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

public class CourseServer {
    public static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    public static void main(String[] args) {
        LOG.info("Starting http server...");
        CourseRepository repo = CourseRepository.openCourseRepository("./courses.db");
        var config = new ResourceConfig().register(new CourseResource(repo));

        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080"), config);

    }
}
