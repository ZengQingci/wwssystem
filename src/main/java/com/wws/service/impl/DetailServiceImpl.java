package com.wws.service.impl;

import com.wws.dao.DetailDao;
import com.wws.dao.OrderDao;
import com.wws.model.TbOrderDetail;
import com.wws.model.Tborder;
import com.wws.service.DetailService;
import com.wws.vo.OrderDetailVO;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    @Resource
    private DetailDao detailDao;
    @Resource
    private OrderDao orderDao;
    /**查询详细订单信息*/
    public PageVO findDetail(Integer currentPage, Integer pageSize, String orderNumber){
        //通过订单编号查询订单id
        Integer orderId = orderDao.findOrderIdByNumber(orderNumber);
        //查询总记录数
        int rowCount = detailDao.getRowCount(orderId);
        //计算起始位置
        int startIndex = (currentPage-1)*pageSize;
        //查询分页信息
        List<TbOrderDetail> list = detailDao.findDetail(startIndex,pageSize,orderId);
        //封装数据
        PageVO pageVO = new PageVO();
        pageVO.setCode(0);
        pageVO.setMsg("ok");
        pageVO.setCount(rowCount);
        pageVO.setData(list);
        return pageVO;
    }
    @Transactional
    @Override
    public void addOrderDetail(OrderDetailVO orderDetailVo) {
        //查询订单是否存在
        String orderNumber = orderDetailVo.getOrderNumber();
        Tborder tborder = orderDao.findOrderByOrderNumber(orderNumber);
        if(StringUtils.isEmpty(tborder)){
            Tborder order = new Tborder();
            order.setOrderNumber(orderNumber);
            order.setOrderStatus("未付款");
            order.setProductCount(1);
            order.setAddress(orderDetailVo.getAddress());
            order.setOrderConsignee(orderDetailVo.getOrderConsignee());
            order.setCustomerId(orderDetailVo.getCustomerId());
            order.setPhone(orderDetailVo.getPhone());
            order.setOrderAmountTotal(orderDetailVo.getInfoPrice());
            order.setProductAmountTotal(orderDetailVo.getInfoPrice());
            orderDao.saveOrder(order);
            //插入详细订单信息(获取刚刚插入的订单的ID)
            Integer orderId = order.getId();
            saveOrderDetail(orderDetailVo,orderId);
        }else {
            //存在,就修改订单的金额和数量
            double productAmountTotal = orderDetailVo.getInfoPrice()+tborder.getProductAmountTotal();
            double orderAmountTotal = orderDetailVo.getInfoPrice()+tborder.getOrderAmountTotal();
            int productCount = tborder.getProductCount()+1;
            orderDao.updateOrder(productAmountTotal,orderAmountTotal,productCount,tborder.getId());
            //添加详细订单（根据订单ID和商品的ID查询记录是否存在）
            TbOrderDetail od = detailDao.findDetailByOrderAndPid(orderDetailVo.getProductId(),tborder.getId());
            if(StringUtils.isEmpty(od)){
                //如果没有查询到记录
                saveOrderDetail(orderDetailVo,tborder.getId());
            }else {
                //如果存在修改小计金额和数量
                double subtotal = orderDetailVo.getInfoPrice()+od.getSubtotal();
                double orderDetailNumber = od.getOrderDetailNumber()+1;
                detailDao.updateOrderDetail(subtotal,orderDetailNumber,od.getId());
            }
        }
    }
    /**删除详细订单*/
    @Transactional
    @Override
    public void delDetail(Integer id) {
        //删除详细订单的同时 还要删除对应的订单
        Integer orderId = detailDao.findOrderId(id);
        //根据订单的ID查询有几条产品的记录
        int count = detailDao.findOrderCount(orderId);
        if(count == 1){
            orderDao.delOrderById(orderId);
        }
        //删除详细订单信息
        detailDao.delDetailById(id);
    }

    /**添加详细订单的方法*/
    private void saveOrderDetail(OrderDetailVO orderDetailVo, Integer orderId) {
        TbOrderDetail tbOrderDetail = new TbOrderDetail();
        tbOrderDetail.setProductId(orderDetailVo.getProductId());
        tbOrderDetail.setOrderId(orderId);
        tbOrderDetail.setProducName(orderDetailVo.getProducName());
        tbOrderDetail.setProductPrice(orderDetailVo.getInfoPrice());
        tbOrderDetail.setOrderDetailNumber(1);
        tbOrderDetail.setDiscountRate(orderDetailVo.getInfoDiscount());
        tbOrderDetail.setSubtotal(orderDetailVo.getInfoPrice());
        tbOrderDetail.setProductMarque(orderDetailVo.getInfoSpec());
        detailDao.saveOrderDetail(tbOrderDetail);
    }
}
