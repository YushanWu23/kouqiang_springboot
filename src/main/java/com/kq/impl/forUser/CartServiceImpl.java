package com.kq.impl.forUser;

/*import com.elm.dao.IBusinessDao;*/
import com.kq.dao.*;
import com.kq.dao.forUser.ICartDao;
import com.kq.dao.forUser.IProductDao;
import com.kq.pojo.*;
import com.kq.pojo.forUser.Cart;
import com.kq.pojo.forUser.Product;
import com.kq.service.forUser.ICartService;
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
    IProductDao iProductDao;
    @Override
    @Transactional
    public List<Cart> getCartAll(String userId){
        return iCartDao.findCartsByUserUserId(userId);
    }
    @Override
    public int insertProductIntoCart(String userId, int productId){
        Cart existingCart = iCartDao.findCartsByUserUserIdAndProductProductId(userId, productId);
        if (existingCart != null) {
            // 如果购物车中已存在该食品，则更新数量
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            iCartDao.save(existingCart);
        } else {
            Cart cart = new Cart();
            cart.setQuantity(1);// // 设置商品数量为1
            Product product = iProductDao.findById(productId).orElse(null);
            // 处理商品不存在的情况，例如抛出异常或返回错误码
            if (product == null) {
                return -1;
            }
            cart.setProduct(product);
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
    public int deleteCart( String userId, int productId){
        Cart cart = iCartDao.findCartsByUserUserIdAndProductProductId(userId,productId);
        iCartDao.delete(cart);
        return 1;
    }
    @Override
    public int updateCart( String userId, int productId, int quantity){
        Cart cart = iCartDao.findCartsByUserUserIdAndProductProductId(userId,productId);
        cart.setQuantity(quantity);
        iCartDao.save(cart);
        return 1;
    }
}
