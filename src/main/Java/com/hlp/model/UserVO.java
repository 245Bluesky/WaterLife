package com.hlp.model;

/**
 * Created by Administrator on 2017/6/11.
 */
public class UserVO {
    private int id;
    private String name;
    private String pwd;

    public UserVO() {

    }

    public UserVO(String name, String pwd) {
        super();
        this.name = name;
        this.pwd = pwd;
    }

    public UserVO(int id, String name, String pwd) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

