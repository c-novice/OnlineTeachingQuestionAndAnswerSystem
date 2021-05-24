package com.service.impl;

import com.dao.CommunityDao;
import com.dao.impl.CommunityDaoImpl;
import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;
import com.service.CommunityService;

import java.util.List;

public class CommunityServiceImpl implements CommunityService {

    private final CommunityDao communityDao = new CommunityDaoImpl();

    @Override
    public void addQuestion(Question question) {
        communityDao.addQuestion(question);
    }

    @Override
    public void addAnswer(String name, Answer answer) {
        communityDao.addAnswer(name, answer);
    }

    @Override
    public void deleteQuestionById(Integer id) {
        communityDao.deleteQuestionById(id);
    }

    @Override
    public void updateQuestion(Question question) {
        communityDao.updateQuestion(question);
    }

    @Override
    public void deleteAnswerByNameAndContext(String name, String context) {
        communityDao.deleteAnswerByNameAndContext(name, context);
    }

    @Override
    public Question queryQuestionById(Integer id) {
        return communityDao.queryQuestionById(id);
    }

    @Override
    public List<Question> queryQuestions() {
        return communityDao.queryQuestions();
    }


    @Override
    public Page<Question> page(int pageNo, int pageSize) {
        Page<Question> page = new Page<Question>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = communityDao.queryForPageTotalCount(null);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        // 求当前页数据
        List<Question> items = communityDao.queryForPageItems(begin, pageSize);

        for (Question item : items) {
            item.setAnswers(communityDao.queryAnswerByName(item.getName()));
        }

        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Question> pageByQuestion(int pageNo, int pageSize, String searchName) {
        Page<Question> page = new Page<Question>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = communityDao.queryForPageTotalCount(searchName);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        // 求当前页数据
        List<Question> items = communityDao.queryForPageItemsBySearchName(begin, pageSize, searchName);

        for (Question item : items) {
            item.setAnswers(communityDao.queryAnswerByName(item.getName()));
        }

        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Question getUsernameToByQuestionName(String questionName) {
        return communityDao.queryUsernameToByQuestionName(questionName);
    }
}
