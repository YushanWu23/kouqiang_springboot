package com.kq.controller;

import com.kq.pojo.Orders;
import com.kq.service.IOrdersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {
    @Resource
    IOrdersService iOrdersService;
    @PostMapping("/createOrders")
    Orders createOrders(@RequestParam String userId, @RequestParam int daId, @RequestParam Double orderTotal ){
        return iOrdersService.createOrders(userId,daId,orderTotal);
    }
    @GetMapping("/getOrdersByOrdersId")
    Orders getOrdersByOrdersId(@RequestParam int orderId){
        return iOrdersService.getOrdersByOrdersId(orderId);
    }
    @GetMapping("/getOrdersByUserId")
    List<Orders> getOrdersByUserId(@RequestParam String userId){//根据用户编号查询此用户的所有订单信息
        return iOrdersService.getOrdersByUserId(userId);
    }
    @GetMapping("/updateOrderState")
    int updateOrderState(@RequestParam int orderId){
        return iOrdersService.updateOrderState(orderId);
    }
}
