package com.dao;

import com.pojo.Answer;
import com.pojo.Question;
import com.pojo.User;

import java.util.List;

public interface CommunityDao {

    //增加问题
    public int addQuestion(Question question);

    //删除问题
    public int deleteQuestionById(Integer id);

    //更新问题
    public int updateQuestion(Question question);

    //根据id查询问题
    public Question queryQuestionById(Integer id);

    //查询所有问题
    public List<Question> queryQuestions();

    //根据问题名查询所有回答
    public List<Answer> queryAnswerByName(String name);

    //根据问题名和回答删除一条回答
    public int deleteAnswerByNameAndContext(String name,String context);

    //查询总页数
    public Integer queryForPageTotalCount();

    //查询当前页下的问题
    public List<Question> queryForPageItems(int begin, int pageSize);

    //添加回答
    public int addAnswer(String name,Answer answer);

    //根据searchName查询当前页下的问题
    List<Question> queryForPageItemsBySearchName(int begin, int pageSize, String searchName);
}
