package com.kq.impl;

import com.kq.dao.IProductDao;
import com.kq.pojo.Product;
import com.kq.service.IProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductServiceImpl implements IProductService {
    @Resource
    IProductDao iProductDao;
    @Override
    public Product getByProductId(int productId){
        return iProductDao.findProductByProductId(productId);
    }
    @Override
    public List<Product> getAll(){
        return iProductDao.findAll();
    }
}
