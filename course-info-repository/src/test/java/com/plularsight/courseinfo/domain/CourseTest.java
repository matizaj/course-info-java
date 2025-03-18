package com.plularsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    public void test(){
        assertThrows(IllegalArgumentException.class, () -> new Course("", "title", "00:05:00", "url"));
    }

}