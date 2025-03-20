package com.ps.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CourseTest {

    @Test
    void createValidCourse() {
        var course = new Course("id", "name", 10, "some url");
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