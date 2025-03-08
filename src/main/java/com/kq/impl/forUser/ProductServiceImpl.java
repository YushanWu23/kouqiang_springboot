package com.kq.impl.forUser;

import com.kq.dao.forUser.IProductDao;
import com.kq.pojo.forUser.Product;
import com.kq.service.forUser.IProductService;
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
