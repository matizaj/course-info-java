package com.ps.courseinfo.domain;

import java.util.Optional;

public record Course(String id, String name, long length, String url, Optional<String> note) {
    public Course{
        filled(id);
        filled(name);
        filled(url);
        note.ifPresent(Course::filled);
    }

    private static void filled(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("no value present!");
        }
    }
}
