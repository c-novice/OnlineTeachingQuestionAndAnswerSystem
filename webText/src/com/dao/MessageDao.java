package com.dao;

import com.pojo.Message;

import java.util.List;

public interface MessageDao  {

    /**
     * 求message数据库中所有的数量
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 根据索引求message数据库中部分message
     * @param begin
     * @param pageSize
     * @return
     */
    List<Message> queryForPageItems(int begin, int pageSize);
}
