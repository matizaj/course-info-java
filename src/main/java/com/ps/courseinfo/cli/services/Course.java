package com.ps.courseinfo.cli.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Course(String id, String title, String contentUrl, boolean isRetired) {
}
