package com.kq.controller.forUser;

import com.kq.pojo.forUser.Orders;
import com.kq.service.forUser.IOrdersService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Map<String, Object>> deleteOrder(@RequestParam int orderId) {
        try {
            boolean success = iOrdersService.deleteOrder(orderId);
            if (success) {
                return ResponseEntity.ok(Map.of("success", true));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "订单删除失败"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "订单删除时发生错误: " + e.getMessage()));
        }
    }
}
