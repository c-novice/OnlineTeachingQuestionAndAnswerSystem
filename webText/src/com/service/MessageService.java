package com.service;

import com.pojo.Message;
import com.pojo.Page;

public interface MessageService {
    //分页&初始化
    Page<Message> page(int pageNo, int pageSize,String username);

    /**
     * 根据id删除消息
     * @param id
     */
    void deleteMessageById(Integer id);

    /**
     *
     */
    void addAnswerFromCommunity(Message message);
}
