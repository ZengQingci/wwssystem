package com.wws.controller;

import com.wws.service.DetailService;
import com.wws.vo.OrderDetailVO;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/detail")
public class DetailController {
    @Resource
    private DetailService detailService;
    @RequestMapping("/findDetail")
    @ResponseBody
    public PageVO findDetail(Integer page,Integer limit,String orderNumber){
        return detailService.findDetail(page,limit,orderNumber);
    }

    @RequestMapping("/addOrderDetail")
    @ResponseBody
    public String addOrderDetail(@RequestBody OrderDetailVO orderDetailVo){
        String result ="";
        try{
            detailService.addOrderDetail(orderDetailVo);
            result = "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**删除详细订单*/
    @RequestMapping("/delDetail")
    @ResponseBody
    public String delDetail(Integer id){
        String result = "";
        try{
            detailService.delDetail(id);
            result = "ok";
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
