package com.ps.courseinfo.cli;

public class CourseRetriever {
    public static void main(String[] args) {
        System.out.println("CourseRetriever Started!");
        if(args.length ==0) {
            System.out.println("Please provide an author name as firts argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch(Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void retrieveCourses(String authorId) {
        System.out.println("Retrieving courses for author: " + authorId);
    }
}
