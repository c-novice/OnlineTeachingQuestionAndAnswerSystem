package com.service;

import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;

import java.util.List;

public interface CommunityService {

    /**
     * 增加问题
     *
     * @param question
     */
    void addQuestion(Question question);

    /**
     * 根据id删除问题
     *
     * @param id
     */
    void deleteQuestionById(Integer id);

    /**
     * 根据name和context删除问题
     *
     * @param name
     * @param context
     */
    void deleteAnswerByNameAndContext(String name, String context);

    /**
     * 更新问题
     *
     * @param question
     */
    void updateQuestion(Question question);

    /**
     * 增加回答
     *
     * @param name
     * @param answer
     */
    void addAnswer(String name, Answer answer);

    /**
     * 查询问题
     *
     * @param id
     * @return
     */
    Question queryQuestionById(Integer id);

    /**
     * 查询所有问题
     *
     * @return
     */
    List<Question> queryQuestions();

    /**
     * 分页和初始化
     *
     * @param pageNo
     * @param pageSize
     * @return
     */

    Page<Question> page(int pageNo, int pageSize);

    /**
     * 内部搜索
     *
     * @param pageNo
     * @param pageSize
     * @param searchName
     * @return
     */
    Page<Question> pageByQuestion(int pageNo, int pageSize, String searchName);

    /**
     * 根据问题名查询创建该问题的用户
     * @param questionName
     * @return
     */
    Question getUsernameToByQuestionName(String questionName);
}
