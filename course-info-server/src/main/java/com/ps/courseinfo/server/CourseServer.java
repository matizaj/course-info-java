package com.ps.courseinfo.server;

import com.ps.courseinfo.domain.Course;
import com.ps.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static String loadDatabaseFilename() {
        try(var propStream = CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties props = new Properties();
            props.load(propStream);
            return props.getProperty("course-info.database");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);

    public static void main(String[] args) {
        LOG.info("Starting http server...");
        LOG.info("Loading db filename.." + loadDatabaseFilename());
        CourseRepository repo = CourseRepository.openCourseRepository(loadDatabaseFilename());
        var config = new ResourceConfig().register(new CourseResource(repo));

        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080"), config);

    }
}
