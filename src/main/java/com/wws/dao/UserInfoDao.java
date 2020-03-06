package com.wws.dao;

import com.wws.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserInfoDao {
    /**查询分页信息数据*/
    List<UserInfo> findAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize, @Param("userName") String userName);
    /**查询总记录数*/
    int getRowCount(@Param("userName") String userName);
    /**删除用户方法*/
    void delUserById(Integer id);
    /**修改用户状态方法*/
    void updatestateById(@Param("id") Integer id,@Param("state") Integer state);
    /**批量删除*/
    void delUserByIds(@Param("ids") Integer[] ids);
    /**添加用户信息*/
    void saveuserinfo(UserInfo tbuserinfo);
    /**根据用户ID查询用户信息*/
    UserInfo findUserById(Integer id);
    /**修改用户信息*/
    void updateUserInfo(UserInfo userInfo);
    /**根据用户名和密码查询用户信息*/
    UserInfo findUserByName(@Param("username") String username,@Param("password") String password);
}
