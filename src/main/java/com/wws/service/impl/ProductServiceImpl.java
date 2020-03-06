package com.wws.service.impl;

import com.wws.dao.ProductDao;
import com.wws.model.TbProductInfo;
import com.wws.service.ProductService;
import com.wws.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    public PageVO findProduct(Integer currentPage, Integer pageSize){
        //查询总记录数
        int rowCount = productDao.getRowCount();
        //计算出起始位置
        int startIndex = (currentPage-1)*pageSize;
        //查询分页信息
        List<TbProductInfo> list = productDao.findProduct(startIndex,pageSize);
        //封装数据
        PageVO pageVO = new PageVO();
        pageVO.setCode(0);
        pageVO.setMsg("ok");
        pageVO.setData(list);
        pageVO.setCount(rowCount);
        return pageVO;
    }
}
