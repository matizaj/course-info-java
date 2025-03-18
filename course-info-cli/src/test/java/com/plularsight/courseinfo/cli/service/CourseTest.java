package com.plularsight.courseinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:05:37, 65
            00:05:33, 5
            00:00:00, 0
            """)
    void durationInMinutes(String input, long expected) {
        var course = new Course("id", "Test course", input, "url", false);
        assertEquals(expected, course.durationInMinutes());
    }
}