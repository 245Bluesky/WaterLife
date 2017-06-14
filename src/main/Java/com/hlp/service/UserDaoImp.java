package com.hlp.service;

/**
 * Created by Administrator on 2017/6/11.
 */
import java.util.List;

import com.hlp.model.UserVO;


public interface UserDaoImp{
    public void insert(UserVO uservo);
    public void delete(int id);
    public void update(UserVO uservo);
    public UserVO selectById(int id);
    public List find();
    public UserVO selectByName(String name,String pwd);
}

