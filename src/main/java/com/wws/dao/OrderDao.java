package com.wws.dao;

import com.wws.model.Tborder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**查询订单信息*/
    List<Tborder> findOrderList(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
    /**获取总记录数*/
    int getRowCount();
    /**删除订单信息*/
    void delOrderById(@Param("id") Integer id);
    /**批量删除订单信息*/
    void delOrders(@Param("ids") Integer[] ids);
    /**根据订单编号查询订单id*/
    Integer findOrderIdByNumber(String orderNumber);
    /**查询订单是否存在*/
    Tborder findOrderByOrderNumber(String orderNumber);
    /**添加订单信息*/
    void saveOrder(Tborder order);
    /**修改订单信息的数量和金额*/
    void updateOrder(@Param("productAmountTotal") double productAmountTotal,
                     @Param("orderAmountTotal") double orderAmountTotal,
                     @Param("productCount") int productCount,
                     @Param("id") Integer id);

    /**查询年销售统计信息*/
    List<Map<String, Object>> findYearData();
    /**查询月销售统计信息*/
    List<Map<String, Object>> findMonthData();
    /**查询季度销售统计信息*/
    List<Map<String, Object>> findSeasonData();
}
