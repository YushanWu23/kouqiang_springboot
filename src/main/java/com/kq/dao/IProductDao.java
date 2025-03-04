package com.kq.dao;

import com.kq.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDao extends JpaRepository<Product,Integer> {
    Product findProductByProductId(int productId);
}
