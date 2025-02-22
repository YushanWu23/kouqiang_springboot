package com.kq.controller;

import com.kq.pojo.OrderDetailet;
import com.kq.service.IOrderDetailetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orderDetailet")
public class OrderDetailetController {
    @Resource
    IOrderDetailetService iOrderDetailetService;

    @GetMapping("/getByOrderId")
    List<OrderDetailet> getByOrderId(@RequestParam int orderId) {
        return iOrderDetailetService.getByOrderId(orderId);
    }

    @GetMapping("/saveOrderDetailetBatch")
    int saveOrderDetailetBatch(@RequestParam List<OrderDetailet> list) {// 批量保存订单明细对象列表
        return iOrderDetailetService.saveOrderDetailetBatch(list);
    }
}
