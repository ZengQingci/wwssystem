package com.wws.service.impl;

import com.wws.dao.OrderDao;
import com.wws.model.Tborder;
import com.wws.service.OrderService;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    public PageVO findOrderList(Integer currentPage, Integer pageSize){
        //获取总记录数
        int rowCount = orderDao.getRowCount();
        //计算出起始位置
        Integer startIndex = (currentPage-1)*pageSize;
        //查询订单分页数
        List<Tborder> list = orderDao.findOrderList(startIndex,pageSize);
        //封装数据
        PageVO pageVO = new PageVO();
        pageVO.setCode(0);
        pageVO.setCount(rowCount);
        pageVO.setData(list);
        pageVO.setMsg("ok");
        return pageVO;
    }
    /**删除订单信息*/
    public void delOrderById(Integer id){
        orderDao.delOrderById(id);
    }
    /**批量删除订单信息*/
    public void delOrders(Integer[] ids){
        orderDao.delOrders(ids);
    }
    /**查询年销售统计信息*/
    @Override
    public List<Map<String, Object>> findYearData() {
        return orderDao.findYearData();
    }
    /**查询月销售统计信息*/
    @Override
    public List<Map<String, Object>> findMonthData() {
        return orderDao.findMonthData();
    }
    /**查询季度销售统计信息*/
    @Override
    public List<Map<String, Object>> findSeasonData() {
        return orderDao.findSeasonData();
    }
}
