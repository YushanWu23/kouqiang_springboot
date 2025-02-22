package com.kq.impl;

/*import com.elm.dao.IBusinessDao;*/
import com.kq.dao.*;
import com.kq.pojo.*;
import com.kq.service.ICartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CartServiceImpl implements ICartService {
    @Resource
    ICartDao iCartDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IFoodDao iFoodDao;
    @Override
    @Transactional
    public List<Cart> getCartAll(String userId){
        return iCartDao.findCartsByUserUserId(userId);
    }
    @Override
    public int insertFoodIntoCart(String userId, int foodId){
        Cart existingCart = iCartDao.findCartsByUserUserIdAndFoodFoodId(userId, foodId);
        if (existingCart != null) {
            // 如果购物车中已存在该食品，则更新数量
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            iCartDao.save(existingCart);
        } else {
            Cart cart = new Cart();
            cart.setQuantity(1);// // 设置商品数量为1
            Food food = iFoodDao.findById(foodId).orElse(null);
            // 处理商品不存在的情况，例如抛出异常或返回错误码
            if (food == null) {
                return -1;
            }
            cart.setFood(food);
            User user = iUserDao.findById(userId).orElse(null);
            if (user == null) {
                return -1;
            }
            cart.setUser(user);
            iCartDao.save(cart);
        }
        return 1;// 添加商品到购物车成功
    }
    @Override
    public int deleteCart( String userId, int foodId){
        Cart cart = iCartDao.findCartsByUserUserIdAndFoodFoodId(userId,foodId);
        iCartDao.delete(cart);
        return 1;
    }
    @Override
    public int updateCart( String userId, int foodId, int quantity){
        Cart cart = iCartDao.findCartsByUserUserIdAndFoodFoodId(userId,foodId);
        cart.setQuantity(quantity);
        iCartDao.save(cart);
        return 1;
    }
}
