package com.cwzl.domain;

import java.io.Serializable;

/**
 * 用户实体类 JavaBean
 */

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String telphone;
    private String email;
    private String birthday;
    private String idcard;
    private String jg;
    private String sex;
    private String zy;

    /**
     * 构造无参方法
     */
    public User() {
    }

    /**
     * 有参
     * @param id
     * @param username
     * @param password
     * @param realname
     * @param telphone
     * @param email
     * @param birthday
     * @param idcard
     * @param jg
     * @param sex
     * @param zy
     */
    public User(int id, String username, String password, String realname, String telphone, String email, String birthday, String idcard, String jg, String sex, String zy) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.telphone = telphone;
        this.email = email;
        this.birthday = birthday;
        this.idcard = idcard;
        this.jg = jg;
        this.sex = sex;
        this.zy = zy;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
