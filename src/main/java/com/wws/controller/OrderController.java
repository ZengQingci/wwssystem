package com.wws.controller;

import com.wws.service.OrderService;
import com.wws.utils.OrderCoderUtil;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 跳转到订单页面
     */
    @RequestMapping("/returnOrderList")
    public String returnOrderList() {
        return "order/orderList";
    }

    /**
     * 跳转到新增订单页面
     */
    @RequestMapping("/returnAddOrder")
    public String returnAddOrder() {
        return "order/addOrder";
    }

    /**
     * 查询订单信息
     */
    @RequestMapping("/findOrderList")
    @ResponseBody
    public PageVO findOrderList(Integer page, Integer limit) {
        return orderService.findOrderList(page, limit);
    }

    /***删除订单信息*/
    @RequestMapping("/delOrderById")
    @ResponseBody
    public String delOrderById(Integer id) {
        String result = "";
        try {
            orderService.delOrderById(id);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delOrders")
    @ResponseBody
    public String delOrders(Integer[] ids) {
        String result = "";
        try {
            orderService.delOrders(ids);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取订单编号
     */
    @RequestMapping("/getOrderCode")
    @ResponseBody
    public String getOrderCode() {
        return OrderCoderUtil.getOrderCode(null);
    }
}
