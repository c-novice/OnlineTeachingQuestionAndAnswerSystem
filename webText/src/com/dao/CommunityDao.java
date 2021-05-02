package com.dao;

import com.pojo.Answer;
import com.pojo.Question;
import com.pojo.User;

import java.util.List;

public interface CommunityDao {

    //增加问题
    int addQuestion(Question question);

    //删除问题
    int deleteQuestionById(Integer id);

    //更新问题
    int updateQuestion(Question question);

    //根据id查询问题
    Question queryQuestionById(Integer id);

    //查询所有问题
    List<Question> queryQuestions();

    //根据问题名查询所有回答
    List<Answer> queryAnswerByName(String name);

    //根据问题名和回答删除一条回答
    int deleteAnswerByNameAndContext(String name, String context);

    //查询总页数
    Integer queryForPageTotalCount();

    //查询当前页下的问题
    List<Question> queryForPageItems(int begin, int pageSize);

    //添加回答
    int addAnswer(String name, Answer answer);

    //根据searchName查询当前页下的问题
    List<Question> queryForPageItemsBySearchName(int begin, int pageSize, String searchName);
}
