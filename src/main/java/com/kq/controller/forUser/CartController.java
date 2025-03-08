package com.kq.controller.forUser;
import com.kq.pojo.forUser.Cart;
import com.kq.service.forUser.ICartService;
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
    @PostMapping("/insertProductIntoCart")//添加商品到购物车
    int insertProductIntoCart(@RequestParam String userId,@RequestParam int productId){
        return iCartService.insertProductIntoCart(userId,productId);
    }
    @PostMapping("/deleteCart")
    int deleteCart(@RequestParam String userId,@RequestParam int productId){
        return iCartService.deleteCart(userId,productId);
    }
    @PostMapping("/updateCart")//该功能已经融入到insertProductIntoCart中
    int updateCart(@RequestParam String userId,@RequestParam int productId,@RequestParam int quantity){
        return iCartService.updateCart(userId,productId,quantity);
    }
}