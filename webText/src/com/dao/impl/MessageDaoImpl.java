package com.dao.impl;

import com.dao.MessageDao;
import com.pojo.Message;

import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public Integer queryForPageTotalCount(String username) {
        String sql = "select count(*) from t_message where usernameTo=?";
        Number count = (Number) queryForSingleValue(sql, username);
        return count.intValue();
    }

    @Override
    public List<Message> queryForPageItems(int begin, int pageSize, String username) {
        String sql = "select * from t_message  where usernameTo=? ORDER BY checked limit ?,?";
        return queryForList(Message.class, sql, username, begin, pageSize);
    }

    @Override
    public Integer deleteMessageById(Integer id) {
        String sql = "delete from t_message where id = ?";
        return update(sql, id);
    }

    @Override
    public Integer setAllMessageRead(String username) {
        String sql = "update t_message set `checked`= 1 where usernameTo =?";
        return update(sql, username);
    }

    @Override
    public Integer addMessage(Message message) {
        String sql = "insert into t_message(`usernameFrom`,`usernameTo`,`context`,`type`,`checked`) values(?,?,?,?,?)";
        return update(sql, message.getUsernameFrom(), message.getUsernameTo(), message.getContext(), message.getType(), message.getChecked());
    }
}
