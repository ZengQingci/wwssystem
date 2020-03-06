package com.wws.controller;

import com.wws.model.Tbcustomer;
import com.wws.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    /**跳转到用户等级统计页面*/
    @RequestMapping("/returnechart")
    public String returnechart(){
        return "echarts/echart";
    }
    /**查询用户等级信息*/
    @RequestMapping("/findStatistic")
    @ResponseBody
    public List<Map<String,Object>> findStatistic(){
        return customerService.findStatistic();
    }
    /**根据姓名获取客户名*/
    @RequestMapping("/getCustomerByName")
    @ResponseBody
    public List<Tbcustomer> getCustomerByName(String name){
        return customerService.getCustomerByName(name);
    }
}
