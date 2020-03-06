package com.wws.dao;

import com.wws.model.TbProductInfo;
import com.wws.vo.PageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**查询产品信息*/
    List<TbProductInfo> findProduct(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
    /**查询产品的总记录数*/
    int getRowCount();
}
