package com.wws.service;

import com.wws.vo.PageVO;

public interface ProductService {
    /**查询产品信息*/
    PageVO findProduct(Integer currentPage,Integer pageSize);
}
