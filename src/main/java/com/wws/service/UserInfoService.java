package com.wws.service;

import com.wws.model.UserInfo;
import com.wws.vo.PageVO;

import java.util.List;

public interface UserInfoService {
    /**
     * 查询用户分页信息的数据
     */
    public PageVO findAll(Integer currentPage, Integer pageSize, String userName);

    /**
     * 删除用户方法
     */
    public void delUserById(Integer id);

    /**
     * 修改用户状态方法
     */
    public void updatestateById(Integer id, Integer state);

    /**
     * 批量删除
     */
    public void delUserByIds(Integer[] ids);

    /**
     * 添加用户信息
     */
    public void saveuserinfo(UserInfo tbuserinfo);

    /**
     * 根据用户ID查询用户信息
     */
    public UserInfo findUserById(Integer id);

    /**
     * 修改用户信息
     */
    public void updateUserInfo(UserInfo userInfo);

    /**
     * 根据用户名和密码查询用户信息
     */
    UserInfo findUserByName(String username, String password);
}
