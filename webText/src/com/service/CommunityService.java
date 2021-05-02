package com.service;

import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;

import java.util.List;

public interface CommunityService {

    void addQuestion(Question question);

    void deleteQuestionById(Integer id);

    void deleteAnswerByNameAndContext(String name, String context);

    void updateQuestion(Question question);

    void addAnswer(String name, Answer answer);

    Question queryQuestionById(Integer id);

    List<Question> queryQuestions();

    Page<Question> page(int pageNo, int pageSize);

    Page<Question> pageByQuestion(int pageNo, int pageSize, String searchName);
}
