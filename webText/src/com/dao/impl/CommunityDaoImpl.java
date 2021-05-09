package com.dao.impl;

import com.dao.CommunityDao;
import com.pojo.Answer;
import com.pojo.Question;

import java.util.List;

public class CommunityDaoImpl extends BaseDao implements CommunityDao {
    @Override
    public int addQuestion(Question question) {
        String sql = "insert into t_question(`name`,`username`) values(?,?)";
        return update(sql, question.getName(), question.getUsername());
    }

    @Override
    public int addAnswer(String name, Answer answer) {
        String sql = "insert into t_answer(`name`,`username`,`context`,`welcomeCount`) values(?,?,?,?)";
        return update(sql, name, answer.getUsername(), answer.getContext(), answer.getWelcomeCount());
    }

    @Override
    public int deleteQuestionById(Integer id) {
        String sql = "delete from t_question where id = ?";
        return update(sql, id);
    }

    @Override
    public int deleteAnswerByNameAndContext(String name, String context) {
        String sql = "delete from t_answer where name = ? and context = ?";
        return update(sql, name, context);
    }

    @Override
    public int updateQuestion(Question question) {
        String sql = "update t_question set `name`= ? , `username`= ? where id = ?";
        return update(sql, question.getName(), question.getUsername(), question.getId());
    }

    @Override
    public Question queryQuestionById(Integer id) {
        String sql = "select * from t_question where id = ?";
        return queryForOne(Question.class, sql, id);
    }

    @Override
    public List<Question> queryQuestions() {
        String sql = "select * ,from t_question";
        return queryForList(Question.class, sql);
    }

    @Override
    public List<Answer> queryAnswerByName(String name) {
        String sql = "select `username` , `context`,`welcomeCount` ,name from t_answer where name = ?";
        return queryForList(Answer.class, sql, name);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_question";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Question> queryForPageItems(int begin, int pageSize) {
        String sql = "select * ,name from t_question limit ?,?";
        return queryForList(Question.class, sql, begin, pageSize);
    }

    @Override
    public List<Question> queryForPageItemsBySearchName(int begin, int pageSize, String searchName) {
        String sql = "select * from t_question where id in ( select `id` from t_question where name like '%' ? '%' ) limit ? , ?";
        return queryForList(Question.class, sql, searchName, begin, pageSize);
    }

    @Override
    public Question queryUsernameToByQuestionName(String questionName) {
        String sql = "select * from t_question where name = ?";
        return queryForOne(Question.class, sql, questionName);
    }
}
