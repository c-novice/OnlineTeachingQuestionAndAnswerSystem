package com.dao;

import com.pojo.Message;

import java.util.List;

public interface MessageDao  {

    /**
     * 求message数据库中所有的数量
     * @return
     */
    Integer queryForPageTotalCount(String username);

    /**
     * 根据索引求message数据库中部分message
     * @param begin
     * @param pageSize
     * @return
     */
    List<Message> queryForPageItems(int begin, int pageSize,String username);

    /**
     * 根据id删除消息
     * @param id
     */
    public Integer deleteMessageById(Integer id);

    /**
     * 根据username设置所有的消息已读
     * @param username
     */
    public Integer setAllMessageRead(String username);
}
