package com.ps.courseinfo.cli.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CourseRetrievalService {
    private static final String url = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();
    public List<Course> getCoursesFor(String authorId){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url.formatted(authorId)))
                .GET().build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return switch(response.statusCode()) {
                case 200 -> getCourses(response);
                case 404 -> List.of();
                default -> throw new RuntimeException("ps api failed with status code: " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call PS api", e);
        }
    }

    private static List<Course> getCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = mapper.getTypeFactory().constructCollectionType(List.class, Course.class);
        return mapper.readValue(response.body(), returnType);
    }
}
