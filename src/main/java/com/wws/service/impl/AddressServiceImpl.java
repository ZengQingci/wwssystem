package com.wws.service.impl;

import com.wws.dao.AddressDao;
import com.wws.model.Tbarea;
import com.wws.model.Tbcity;
import com.wws.model.Tbprovince;
import com.wws.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDao addressDao;

    /**
     * 查询省级信息
     */
    public List<Tbprovince> findProvince() {
        return addressDao.findProvince();
    }

    /**
     * 查询市级信息
     */
    public List<Tbcity> findCity(String provincecode) {
        return addressDao.findCity(provincecode);
    }

    /**
     * 查询县级信息
     */
    public List<Tbarea> findArea(String citycode) {
        return addressDao.findArea(citycode);
    }
}
