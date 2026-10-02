package com.eventrouter.intake.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubIssuePayload {

    private String action;
    private Issue issue;
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public Issue getIssue() { return issue; }
    public void setIssue(Issue issue) { this.issue = issue; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Issue {
        private String title;
        private String body;
        private Integer number;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
        public Integer getNumber() { return number; }
        public void setNumber(Integer number) { this.number = number; }
    }
}