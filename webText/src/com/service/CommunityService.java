package com.service;

import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;

import java.util.List;

public interface CommunityService {

    public void addQuestion(Question question);

    public void deleteQuestionById(Integer id);

    public void deleteAnswerByNameAndContext(String name,String context);

    public void updateQuestion(Question question);

    public void addAnswer(String name, Answer answer);

    public Question queryQuestionById(Integer id);

    public List<Question> queryQuestions();

    public Page<Question> page(int pageNo, int pageSize);
}
