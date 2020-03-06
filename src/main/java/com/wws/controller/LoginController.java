package com.wws.controller;

import com.wws.model.UserInfo;
import com.wws.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserInfoService userInfoService;
    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(String username, String password, String code, HttpSession httpSession){
        String result = "1";
        String session_code = (String)httpSession.getAttribute("CODE");
        UserInfo userInfo = userInfoService.findUserByName(username,password);
        if(!code.equalsIgnoreCase(session_code)){
            result = "3";
        }else if(StringUtils.isEmpty(userInfo)){
            result = "2";
        }else if(userInfo.getState() == 0){
            result = "4";
        }
        return result;
    }
    /**登录成功跳转页面*/
    @RequestMapping("/returnLogin")
    public String returnLogin(){
        return "home";
    }
}
