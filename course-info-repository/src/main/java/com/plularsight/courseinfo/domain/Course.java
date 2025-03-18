package com.plularsight.courseinfo.domain;

public record Course(String id, String title, String duration, String Url) {
    public Course{
        filled(id);
        filled(title);
        filled(duration);
        filled(Url);
    }

    private static void filled(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException("No value!");
        }
    }
}
