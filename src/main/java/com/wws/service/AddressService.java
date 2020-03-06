package com.wws.service;

import com.wws.model.Tbarea;
import com.wws.model.Tbcity;
import com.wws.model.Tbprovince;

import java.util.List;

public interface AddressService {
    /**
     * 查询省级信息
     */
    public List<Tbprovince> findProvince();

    /**
     * 查询市级信息
     */
    public List<Tbcity> findCity(String provincecode);

    /**
     * 查询县级信息
     */
    public List<Tbarea> findArea(String citycode);
}
