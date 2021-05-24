package com.service.impl;

import com.dao.AnswerDao;
import com.dao.impl.AnswerDaoImpl;
import com.pojo.Page;
import com.pojo.Question;
import com.service.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao answerDao = new AnswerDaoImpl();

    @Override
    public Page<Question> page(int pageNo, int pageSize, String username) {
        Page<Question> page = new Page<Question>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = answerDao.queryForPageTotalCount(null, username);
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
        List<Question> items = answerDao.queryForPageItems(begin, pageSize, username);

        for (Question item : items) {
            item.setAnswers(answerDao.queryAnswerByName(item.getName()));
        }

        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Question> pageByQuestion(int pageNo, int pageSize, String searchName, String username) {
        Page<Question> page = new Page<Question>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = answerDao.queryForPageTotalCount(searchName, username);
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
        List<Question> items = answerDao.queryForPageItemsBySearchName(begin, pageSize, searchName, username);

        for (Question item : items) {
            item.setAnswers(answerDao.queryAnswerByName(item.getName()));
        }

        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public void deleteQuestionById(Integer id) {
        answerDao.deleteQuestionById(id);
    }
}
