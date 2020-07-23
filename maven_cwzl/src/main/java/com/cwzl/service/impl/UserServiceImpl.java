package com.cwzl.service.impl;

import com.cwzl.dao.UserDao;
import com.cwzl.dao.impl.UserDaoImpl;
import com.cwzl.domain.User;
import com.cwzl.domain.Userinfo;
import com.cwzl.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户方法
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if (u!=null){
            //用户存在，注册失败
            return false;
        }
        userDao.save(user);
        //保存注册信息
        return true;
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User u = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return u;


    }

    /**
     * 分页查找所有用户
     * @param _page
     * @param _limit
     * @return
     */
    @Override
    public Userinfo<User> findUser(String _page, String _limit) {
        int page = Integer.parseInt(_page);
        int limit = Integer.parseInt(_limit);

        Userinfo<User> pl = new Userinfo<>();

        //调用dao查询
        int count = userDao.findCount();
        pl.setCount(count);
        //调用Dao查询List集合
        int start = (page-1)*limit;
        List<User> data = userDao.findByPage(start,limit);
        pl.setData(data);
        return pl;
    }
}
