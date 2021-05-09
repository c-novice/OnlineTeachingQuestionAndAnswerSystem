package com.pojo;

import java.util.List;

public class Question {
    private Integer id;
    private String name;
    private String username;
    private List<Answer> answers;

    public Question() {
    }

    public Question(Integer id, String name, String username, List<Answer> answers) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.answers = answers;
    }

    public Question(String newQuestionName, String username) {
        this.name=newQuestionName;
        this.username=username;
    }

    public Question(Integer id, String name, String username) {
        this.id=id;
        this.name=name;
        this.username=username;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
