package com.dao.impl;

import com.dao.MessageDao;
import com.pojo.Message;

import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public Integer queryForPageTotalCount(String username) {
        String sql = "select count(*) from t_message";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Message> queryForPageItems(int begin, int pageSize,String username) {
        String sql = "select `id` , `usernameFrom`,`usernameTo`,`context` ,`type`,`checked` from t_message limit ?,?";
        //  String sql ="select * from t_message where id in ( select `id` from t_message where usernameTo like  ? ) limit ? , ?";

        return queryForList(Message.class, sql, begin, pageSize);
    }

    @Override
    public Integer deleteMessageById(Integer id) {
        String sql = "delete from t_message where id = ?";
        System.out.println(update(sql,id));
        return update(sql, id);
    }
}
