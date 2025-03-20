package com.ps.courseinfo.cli.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PsCourse(String id, String title, String duration, String contentUrl, boolean isRetired) {
    public long durationInMinutes(){
        return Duration.between(LocalTime.MIN, LocalTime.parse(duration())).toMinutes();
    }
}
