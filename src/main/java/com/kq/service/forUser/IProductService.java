package com.kq.service.forUser;

import com.kq.pojo.forUser.Product;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProductService {
   Product getByProductId( int productId);
    List<Product> getAll();
}
