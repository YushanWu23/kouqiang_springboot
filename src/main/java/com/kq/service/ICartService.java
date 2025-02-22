package com.kq.service;

import com.kq.pojo.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {
    List<Cart> getCartAll(String userId);
    int insertFoodIntoCart(String userId,int foodId);
    int deleteCart(String userId, int foodId);
    int updateCart(String userId, int foodId, int quantity);
}