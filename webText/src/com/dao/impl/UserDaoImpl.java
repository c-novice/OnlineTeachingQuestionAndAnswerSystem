package com.dao.impl;

import com.dao.UserDao;
import com.pojo.Question;
import com.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set `username`=?,`password`=?,`email`=? where id = ?";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_user";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<User> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from t_user limit ?,?";
        return queryForList(User.class, sql, begin, pageSize);
    }

    @Override
    public int deleteUserById(Integer id) {
        String sql = "delete from t_user where id = ?";
        return update(sql, id);
    }

    @Override
    public List<User> queryForPageItemsBySearchName(int begin, int pageSize, String searchName) {
        String sql = "select * from t_user where id in ( select `id` from t_user where username like '%' ? '%' ) limit ? , ?";
        return queryForList(User.class, sql, searchName,begin,pageSize);
    }
}
