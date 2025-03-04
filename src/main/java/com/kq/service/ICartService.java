package com.kq.service;

import com.kq.pojo.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {
    List<Cart> getCartAll(String userId);
    int insertProductIntoCart(String userId,int productId);
    int deleteCart(String userId, int productId);
    int updateCart(String userId, int productId, int quantity);
}