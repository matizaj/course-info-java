package com.ps.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class CourseTest {

    @Test
    void createValidCourse() {
        var course = new Course("id", "name", 10, "some url", Optional.of("note"));
        assertDoesNotThrow(()->course.name(), "");
    }

//    @Test
//    void createInvalidCourse() {
//        var course = new Course("id", "", 10, "some url");
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->course.id(), "id");
//
//        assertTrue(thrown.getMessage().contains("no value present!"));
//    }


}