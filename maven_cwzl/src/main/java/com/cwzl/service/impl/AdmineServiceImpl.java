package com.cwzl.service.impl;


import com.cwzl.dao.AdmineDao;
import com.cwzl.dao.impl.AdmineDaoImpl;
import com.cwzl.domain.Admine;
import com.cwzl.service.AdmineService;

public class AdmineServiceImpl implements AdmineService {
     private AdmineDao admineDao = new AdmineDaoImpl();
    /**
     * 管理员登录
     * @param admine
     * @return
     */
    @Override
    public Admine login(Admine admine) {
        return admineDao.findByUsernameAndPassword(admine.getUsername(),admine.getPassword());
    }
}
