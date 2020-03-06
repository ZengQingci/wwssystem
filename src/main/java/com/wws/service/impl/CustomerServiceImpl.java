package com.wws.service.impl;

import com.wws.dao.CustomerDao;
import com.wws.model.Tbcustomer;
import com.wws.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    /**
     * 查询用户等级信息
     */
    public List<Map<String, Object>> findStatistic() {
        return customerDao.findStatistic();
    }

    /**
     * 查询用户名
     */
    public List<Tbcustomer> getCustomerByName(String name) {
        return customerDao.getCustomerByName(name);
    }
}
