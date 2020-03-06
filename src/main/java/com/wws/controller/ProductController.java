package com.wws.controller;

import com.wws.service.ProductService;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @RequestMapping("/findProduct")
    @ResponseBody
    public PageVO findProduct(Integer page,Integer limit){
        return productService.findProduct(page,limit);
    }
}
