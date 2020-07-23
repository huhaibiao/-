package com.cwzl.service;

import com.cwzl.domain.User;
import com.cwzl.domain.Userinfo;

public interface UserService {
    /**
     * 注册用户方法
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 分页查询用户
     * @param page
     * @param limit
     * @return
     */
    Userinfo<User> findUser(String page, String limit);
}
