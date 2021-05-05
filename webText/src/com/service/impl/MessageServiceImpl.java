package com.service.impl;

import com.dao.MessageDao;
import com.dao.impl.MessageDaoImpl;
import com.pojo.Message;
import com.pojo.Page;

import java.util.List;

public class MessageServiceImpl implements com.service.MessageService {
    private final MessageDao messageDao = new MessageDaoImpl();

    @Override
    public Page<Message> page(int pageNo, int pageSize) {
        Page<Message> page = new Page<Message>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = messageDao.queryForPageTotalCount();
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
        List<Message> items = messageDao.queryForPageItems(begin, pageSize);

        // 设置当前页数据
        page.setItems(items);
        return page;
    }
}
