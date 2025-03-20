package com.ps.courseinfo.server;

import com.ps.courseinfo.domain.Course;
import com.ps.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {
    public static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository repo;

    public CourseResource(CourseRepository repo) {
        this.repo = repo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return repo.getAll().stream().sorted(Comparator.comparing(Course::id)).collect(Collectors.toList());
    }

    @POST
    @Path("/{id}/note")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNote(@PathParam("id") String id, String note) {
        repo.addNote(id, note);
    }
}
