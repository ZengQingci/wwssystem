package com.wws.controller;

import com.wws.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    private OrderService orderService;

    /**
     * 跳转到年销售统计页面
     */
    @RequestMapping("/returnYear")
    public String returnYear() {
        return "echarts/year";
    }

    /**
     * 跳转到季销售统计页面
     */
    @RequestMapping("/returnSeason")
    public String returnSeason() {
        return "echarts/season";
    }

    /***跳转到月销售统计页面*/
    @RequestMapping("/returnMonth")
    public String returnMonth() {
        return "echarts/month";
    }

    /**
     * 查询订单年销售统计信息
     */
    @RequestMapping("/findYearData")
    @ResponseBody
    public List<Map<String, Object>> findYearData() {
        return orderService.findYearData();
    }

    /**
     * 查询订单月销售统计信息
     */
    @RequestMapping("/findMonthData")
    @ResponseBody
    public List<Map<String, Object>> findMonthData() {
        return orderService.findMonthData();
    }

    /**
     * 查询季销售统计信息
     */
    @RequestMapping("/findSeasonData")
    @ResponseBody
    public List<Map<String, Object>> findSeasonData() {
        return orderService.findSeasonData();
    }
}
