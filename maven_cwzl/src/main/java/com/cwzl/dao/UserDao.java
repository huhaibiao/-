package com.cwzl.dao;

import com.cwzl.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);
    /**
     * 保存注册信息
     * @param user
     */
    public void save(User user);

    public User findByUsernameAndPassword(String username,String password);

    /**
     * 查询总记录数
     * @return
     */
    int findCount();

    List<User> findByPage(int start, int limit);

    /**
     * 查询用户数据
     * @return
     */

}
