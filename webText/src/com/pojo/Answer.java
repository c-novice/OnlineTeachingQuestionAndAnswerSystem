package com.pojo;

public class Answer {
    private String username;
    private String context;
    private Integer welcomeCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getWelcomeCount() {
        return welcomeCount;
    }

    public void setWelcomeCount(Integer welcomeCount) {
        this.welcomeCount = welcomeCount;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "username='" + username + '\'' +
                ", context='" + context + '\'' +
                ", welcomeCount=" + welcomeCount +
                '}';
    }

    public Answer() {

    }

    public Answer(String username, String context) {
        this.username = username;
        this.context = context;
    }

    public Answer(String username, String context, Integer welcomeCount) {
        this.username = username;
        this.context = context;
        this.welcomeCount = welcomeCount;
    }
}
