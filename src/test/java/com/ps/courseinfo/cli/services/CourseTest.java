package com.ps.courseinfo.cli.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:37, 5
            01:05:37, 65
            00:00:37, 0
            """)
    void durationInMinutes(String input, long expected) {
        var course = new Course("id", "test course", input, "url", false);
        assertEquals(expected, course.durationInMinutes());
    }

}