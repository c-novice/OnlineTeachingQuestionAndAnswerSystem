package com.dao.impl;

import com.dao.AnswerDao;
import com.pojo.Answer;
import com.pojo.Question;

import java.util.List;

public class AnswerDaoImpl extends BaseDao implements AnswerDao {
    @Override
    public Integer queryForPageTotalCount(String searchName, String username) {
        Number count;
        if (searchName == null) {
            String sql = "select count(*) from t_question where username = ?";
            count = (Number) queryForSingleValue(sql, username);
        } else {
            String sql = "select count(*) from t_question where username = ? and name like '%' ? '%' ";
            count = (Number) queryForSingleValue(sql, username, searchName);
        }
        return count.intValue();
    }

    @Override
    public List<Question> queryForPageItems(int begin, int pageSize, String username) {
        String sql = "select *  from t_question where username = ? limit ?,?";
        return queryForList(Question.class, sql, username, begin, pageSize);
    }

    @Override
    public List<Answer> queryAnswerByName(String name) {
        String sql = "select `username` , `context`,`welcomeCount` ,name from t_answer where name = ?";
        return queryForList(Answer.class, sql, name);
    }

    @Override
    public List<Question> queryForPageItemsBySearchName(int begin, int pageSize, String searchName, String username) {
        String sql = "select * from t_question where id in ( select `id` from t_question where name like '%' ? '%' and username = ? ) limit ? , ?";
        return queryForList(Question.class, sql, searchName, username, begin, pageSize);
    }

    @Override
    public Integer deleteQuestionById(Integer id) {
        String sql = "delete from t_question where id = ?";
        return update(sql, id);
    }

    @Override
    public Integer queryForPage2TotalCount(String searchName, String username) {
        Number count;
        if (searchName == null) {
            String sql = "select count(*) from t_answer where username = ?";
            count = (Number) queryForSingleValue(sql, username);
        } else {
            String sql = "select count(*) from t_answer where username = ? and name like '%' ? '%' ";
            count = (Number) queryForSingleValue(sql, username, searchName);
        }
        return count.intValue();
    }

    @Override
    public List<Answer> queryForPage2Items(int begin, int pageSize, String username) {
        String sql = "select *  from t_answer where username = ? limit ?,?";
        return queryForList(Answer.class, sql, username, begin, pageSize);
    }

    @Override
    public int deleteAnswerByNameAndContext(String name, String context) {
        String sql = "delete from t_answer where name = ? and context = ?";
        return update(sql, name, context);
    }
}
