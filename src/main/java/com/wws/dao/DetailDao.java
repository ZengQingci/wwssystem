package com.wws.dao;

import com.wws.model.TbOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailDao {
    /**查询详细订单的总记录数*/
    int getRowCount(@Param("orderId") Integer orderId);
    /**查询详细订单的分页信息*/
    List<TbOrderDetail> findDetail(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize,@Param("orderId") Integer orderId);
    /**插入详细订单信息*/
    void saveOrderDetail(TbOrderDetail tbOrderDetail);
    /**根据订单ID和商品ID查询记录是否存在*/
    TbOrderDetail findDetailByOrderAndPid(@Param("productId") Integer productId, @Param("orderId") Integer orderId);
    /**修改数量和金额*/
    void updateOrderDetail(@Param("subtotal") double subtotal,@Param("orderDetailNumber") double orderDetailNumber,@Param("id") Integer id);
    /**查询对应的订单ID*/
    Integer findOrderId(Integer id);
    /**查询订单下有几条产品*/
    int findOrderCount(Integer orderId);
    /**删除详细订单*/
    void delDetailById(Integer id);
}
