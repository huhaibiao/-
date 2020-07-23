package com.cwzl.dao.impl;


import com.cwzl.dao.AdmineDao;
import com.cwzl.domain.Admine;
import com.cwzl.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdmineDaoImpl implements AdmineDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admine findByUsernameAndPassword(String username, String password) {
        Admine admine = null;

        try {
            String sql = "select * from admine where username = ? and password = ?";
            //执行sql
            admine = template.queryForObject(sql, new BeanPropertyRowMapper<Admine>(Admine.class), username,password);
        } catch (Exception e) {

        }

        return admine;
    }
}
