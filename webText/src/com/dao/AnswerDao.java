package com.dao;

import com.pojo.Answer;
import com.pojo.Question;

import java.util.List;

public interface AnswerDao {

    /**
     * 返回该用户的问题总数
     *
     * @param username
     * @return
     */
    Integer queryForPageTotalCount(String searchName, String username);

    /**
     * 查询当页问题
     *
     * @param begin
     * @param pageSize
     * @return
     */
    List<Question> queryForPageItems(int begin, int pageSize, String username);

    /**
     * 查询问题
     *
     * @param name
     * @return
     */
    List<Answer> queryAnswerByName(String name);

    /**
     * 根据searchName查询当前页下的问题
     *
     * @param begin
     * @param pageSize
     * @param searchName
     * @param username
     * @return
     */
    List<Question> queryForPageItemsBySearchName(int begin, int pageSize, String searchName, String username);

    /**
     * 根据id删除问题
     *
     * @param id
     */
    Integer deleteQuestionById(Integer id);

    /**
     * 我的回答数
     *
     * @param searchName
     * @param username
     * @return
     */
    Integer queryForPage2TotalCount(String searchName, String username);

    /**
     * 查询回答
     *
     * @param begin
     * @param pageSize
     * @param username
     * @return
     */
    List<Answer> queryForPage2Items(int begin, int pageSize, String username);

    //根据问题名和回答删除一条回答
    int deleteAnswerByNameAndContext(String name, String context);
}
