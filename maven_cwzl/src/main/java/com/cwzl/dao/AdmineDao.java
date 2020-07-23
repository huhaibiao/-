package com.cwzl.dao;


import com.cwzl.domain.Admine;

public interface AdmineDao {

    Admine findByUsernameAndPassword(String username, String password);
}
