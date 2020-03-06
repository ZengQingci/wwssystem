package com.wws.dao;

import com.wws.model.Tbarea;
import com.wws.model.Tbcity;
import com.wws.model.Tbprovince;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {
    /**
     * 查询省级信息
     */
    List<Tbprovince> findProvince();

    /**
     * 查询市级信息
     */
    List<Tbcity> findCity(@Param("provincecode") String provincecode);

    /**
     * 查询县级信息
     */
    List<Tbarea> findArea(@Param("citycode") String citycode);
}
