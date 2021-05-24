package com.pojo;

public class Answer {
    private String name;
    private String username;
    private String context;
    private Integer welcomeCount = 0;

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

    public String getName() {
        return name;
    }

    public void setName(String questionName) {
        this.name = questionName;
    }


    @Override
    public String toString() {
        return "Answer{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", context='" + context + '\'' +
                ", welcomeCount=" + welcomeCount +
                '}';
    }

    public Answer() {

    }

    public Answer(String name, String username, String context, Integer welcomeCount) {
        this.name = name;
        this.username = username;
        this.context = context;
        this.welcomeCount = welcomeCount;
    }

    public Answer(String username, String context, Integer welcomeCount) {
        this.username = username;
        this.context = context;
        this.welcomeCount = welcomeCount;
    }
}
