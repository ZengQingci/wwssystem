package com.wws.controller;

import com.wws.model.Tbarea;
import com.wws.model.Tbcity;
import com.wws.model.Tbprovince;
import com.wws.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    private Logger logger = Logger.getLogger(AddressController.class);
    @Resource
    private AddressService addressService;

    /**
     * 查询省级信息
     */
    @RequestMapping("/findProvince")
    @ResponseBody
    public List<Tbprovince> findProvince() {
        return addressService.findProvince();
    }

    /**
     * 查询市级信息
     */
    @RequestMapping("/findCity")
    @ResponseBody
    public List<Tbcity> findCity(String provincecode) {
        //logger.info("provinceCode======"+provincecode);
        return addressService.findCity(provincecode);
    }

    /**
     * 查询县级信息
     */
    @RequestMapping("/findArea")
    @ResponseBody
    public List<Tbarea> findArea(String citycode) {
        //logger.info("citycode===="+citycode);
        return addressService.findArea(citycode);
    }
}
