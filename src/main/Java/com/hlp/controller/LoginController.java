package com.hlp.controller;

/**
 * Created by Administrator on 2017/6/12.
 */

import com.hlp.dao.UserDao;
import com.hlp.model.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/WaterLife/*")
public class LoginController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, ModelMap modelMap){
        //从页面获取输入的用户名、密码
        String userName=request.getParameter("userName");
        String userPwd=request.getParameter("userPwd");
        // 判断页面输入的用户名、密码是否为 空（"" 或 null）
        if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userPwd)) {
            UserVO userVO = userDao.selectByName(userName, userPwd);
            if (userVO != null) {
                modelMap.addAttribute("userVO", userVO);
                return "/views/main";
            } else {
                request.setAttribute("errorCode", "用户名或密码错误");
                return "/views/login";
            }
        } else {
            request.setAttribute("errorCode", "用户名或密码错误");
            return "/views/login";
        }
    }

}

