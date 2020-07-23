package com.cwzl.dao.impl;

import com.cwzl.dao.UserDao;
import com.cwzl.domain.User;
import com.cwzl.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        //定义sql语句
        User user = null;
        try {
            String sql = "select * from vipuser1 where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
//        System.out.println(user);
        return user;
    }

    /**
     * 保存注册信息
     * @param user
     */
    @Override
    public void save(User user) {
        //定义保存sql
        String sql = "insert into vipuser1(username,password,realname,telphone,email,birthday,jg,sex,zy)"+
                "value(?,?,?,?,?,?,?,?,?)";

        //执行sql
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getRealname(),
                user.getTelphone(),
                user.getEmail(),
                user.getBirthday(),
                user.getJg(),
                user.getSex(),
                user.getZy());
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;

        try {
            String sql = "select * from vipuser1 where username = ? and password = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }
//        System.out.println(user);
        return user;
    }

    @Override
    public int findCount() {
        String sql = "select count(*) from vipuser1";
        Integer integer = template.queryForObject(sql, Integer.class);
//        System.out.println(integer);
        return integer;
    }

    @Override
    public List<User> findByPage(int start, int limit) {
        String sql = "select * from vipuser1 limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,limit);
    }


}

