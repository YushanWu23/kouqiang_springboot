package com.kq.service;

import com.kq.pojo.Product;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProductService {
   Product getByProductId( int productId);
    List<Product> getAll();
}
