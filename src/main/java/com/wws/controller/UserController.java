package com.wws.controller;

import com.wws.model.UserInfo;
import com.wws.service.UserInfoService;
import com.wws.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/returnHome")
    public String returnHome() {
        return "home";
    }

    /**
     * 跳转到用户列表页面
     */
    @RequestMapping("/returnList")
    public String returnList() {
        return "user/userList";
    }

    /**
     * 跳转到添加用户信息页面
     */
    @RequestMapping("/returnAddUserInfo")
    public String returnAddUserInfo() {
        return "user/addUserInfo";
    }

    /**
     * 跳转到编辑用户信息页面
     */
    @RequestMapping("/returnEditUserInfo")
    public ModelAndView returnEditUserInfo(Integer id) {
        UserInfo userInfo = userInfoService.findUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.setViewName("user/editUserInfo");
        return modelAndView;
    }

    /**
     * 查询分页信息
     */
    @RequestMapping("/findUsers")
    @ResponseBody
    public PageVO findUsers(Integer page, Integer limit, String userName) {
        PageVO vo = userInfoService.findAll(page, limit, userName);
        return vo;
    }

    /**
     * 删除用户方法
     */
    @RequestMapping("/delUserById")
    @ResponseBody
    public String delUserById(Integer id) {
        String result = "";
        try {
            userInfoService.delUserById(id);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delUserByIds")
    @ResponseBody
    public String delUserByIds(Integer[] ids) {
        System.out.println(Arrays.asList(ids));
        String result = "";
        try {
            userInfoService.delUserByIds(ids);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改用户状态方法
     */
    @RequestMapping("/updatestate")
    @ResponseBody
    public String updatestateById(Integer id, boolean flag) {
        Integer state = flag == true ? 1 : 0;
        String result = "";
        try {
            userInfoService.updatestateById(id, state);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加用户信息
     */
    @RequestMapping("/saveuserinfo")
    @ResponseBody
    public String saveuserinfo(@RequestBody UserInfo tbuserinfo) {
        String result = "error";
        try {
            userInfoService.saveuserinfo(tbuserinfo);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(@RequestBody UserInfo userInfo) {
        String result = "error";
        try {
            userInfoService.updateUserInfo(userInfo);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
