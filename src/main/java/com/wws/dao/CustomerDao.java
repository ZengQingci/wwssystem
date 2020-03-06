package com.wws.dao;

import com.wws.model.Tbcustomer;

import java.util.List;
import java.util.Map;

public interface CustomerDao {
    /**查询用户等级信息*/
    public List<Map<String,Object>> findStatistic();
    /**查询用户名*/
    public List<Tbcustomer> getCustomerByName(String name);
}
