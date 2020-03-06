package com.wws.service.impl;

import com.wws.dao.UserInfoDao;
import com.wws.model.UserInfo;
import com.wws.service.UserInfoService;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public PageVO findAll(Integer currentPage,Integer pageSize, String userName){
        //1.查询总记录数
        int totalCount = userInfoDao.getRowCount(userName);
        //2.计算出起始位置
        int startIndex = (currentPage-1)*pageSize;
        //3.查询出分页信息数据
        List<UserInfo> list = userInfoDao.findAll(startIndex,pageSize, userName);
        //封装数据
        PageVO vo = new PageVO();
        vo.setCode(0);
        vo.setMsg("ok");
        vo.setCount(totalCount);
        vo.setData(list);
        return vo;
    }
    /**删除用户方法*/
    public void delUserById(Integer id){
        userInfoDao.delUserById(id);
    }
    /**批量删除*/
    public void delUserByIds(Integer[] ids){
        userInfoDao.delUserByIds(ids);
    }
    /**修改用户状态方法*/
    public void updatestateById(Integer id,Integer state){
        userInfoDao.updatestateById(id,state);
    }
    /**添加用户信息*/
    public void saveuserinfo(UserInfo tbuserinfo){
        userInfoDao.saveuserinfo(tbuserinfo);
    }
    /**根据用户ID查询用户信息*/
    public UserInfo findUserById(Integer id){
        return userInfoDao.findUserById(id);
    }
    /**修改用户信息*/
    public void updateUserInfo(UserInfo userInfo){
        userInfoDao.updateUserInfo(userInfo);
    }
    /**根据用户名和密码查询用户信息*/
    @Override
    public UserInfo findUserByName(String username, String password) {
        return userInfoDao.findUserByName(username,password);
    }
}
