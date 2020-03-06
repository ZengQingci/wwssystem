package com.wws.service;

import com.wws.vo.PageVO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 查询订单信息
     */
    PageVO findOrderList(Integer currentPage, Integer pageSize);

    /**
     * 删除订单信息
     */
    void delOrderById(Integer id);

    /**
     * 批量删除订单信息
     */
    void delOrders(Integer[] ids);

    /**
     * 查询年销售统计信息
     */
    List<Map<String, Object>> findYearData();

    /**
     * 查询月销售统计信息
     */
    List<Map<String, Object>> findMonthData();

    /**
     * 查询季度销售统计信息
     */
    List<Map<String, Object>> findSeasonData();
}
