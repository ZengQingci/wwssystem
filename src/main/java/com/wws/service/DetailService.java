package com.wws.service;

import com.wws.vo.OrderDetailVO;
import com.wws.vo.PageVO;

public interface DetailService {
    /**查询详细订单信息*/
    PageVO findDetail(Integer page, Integer limit, String orderNumber);

    /**添加订单*/
    void addOrderDetail(OrderDetailVO orderDetailVo);
    /**根据ID删除详细订单*/
    void delDetail(Integer id);
}
