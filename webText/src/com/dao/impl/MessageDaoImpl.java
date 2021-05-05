package com.dao.impl;

import com.dao.MessageDao;
import com.pojo.Message;

import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_message";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Message> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `usernameFrom`,`usernameTo`,`context` ,`type`,`checked` from t_message limit ?,?";
        return queryForList(Message.class, sql, begin, pageSize);
    }

}
