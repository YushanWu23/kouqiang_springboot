package com.kq.controller;
import com.kq.pojo.Cart;
import com.kq.service.ICartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Resource
    ICartService iCartService;
    @PostMapping("/getCartAll")//显示该购物车内容
    List<Cart> getCartAll(@RequestParam String userId){
        return iCartService.getCartAll(userId);
    }
    @PostMapping("/insertFoodIntoCart")//添加商品到购物车
    int insertFoodIntoCart(@RequestParam String userId,@RequestParam int foodId){
        return iCartService.insertFoodIntoCart(userId,foodId);
    }
    @PostMapping("/deleteCart")
    int deleteCart(@RequestParam String userId,@RequestParam int foodId){
        return iCartService.deleteCart(userId,foodId);
    }
    @PostMapping("/updateCart")//该功能已经融入到insertFoodIntoCart中
    int updateCart(@RequestParam String userId,@RequestParam int foodId,@RequestParam int quantity){
        return iCartService.updateCart(userId,foodId,quantity);
    }
}