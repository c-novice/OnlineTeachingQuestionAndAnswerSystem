package com.service;

import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;

public interface AnswerService {

    /**
     * 分页和初始化
     *
     * @param pageNo
     * @param pageSize
     * @param username
     * @return
     */
    Page<Question> page(int pageNo, int pageSize, String username);

    /**
     * 根据关键字查问题
     *
     * @param pageNo
     * @param pageSize
     * @param searchName
     * @param username
     * @return
     */
    Page<Question> pageByQuestion(int pageNo, int pageSize, String searchName, String username);

    /**
     * 根据id删除问题
     *
     * @param id
     */
    void deleteQuestionById(Integer id);

    /**
     * 处理我的回答的分页和初始化
     *
     * @param pageNo
     * @param pageSize
     * @param username
     * @return
     */
    Page<Answer> page2(int pageNo, int pageSize, String username);

    /**
     * 根据name和context删除问题
     *
     * @param name
     * @param context
     */
    void deleteAnswerByNameAndContext(String name, String context);
}
