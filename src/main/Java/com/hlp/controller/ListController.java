package com.hlp.controller;

/**
 * Created by Administrator on 2017/6/12.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlp.dao.UserDao;
import com.hlp.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/WaterLife/*")
public class ListController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/List")
    public String showBlogs(ModelMap modelMap) {
        List<UserVO> userList = userDao.find();

        modelMap.addAttribute("userList", userList);

        return "views/list";
    }
}

