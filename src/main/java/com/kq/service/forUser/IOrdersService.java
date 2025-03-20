package com.kq.service.forUser;
import com.kq.pojo.forUser.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrdersService {
    Orders createOrders(String userId, int daId, Double orderTotal);
    Orders getOrdersByOrdersId(int orderId);
    List<Orders> getOrdersByUserId(String userId);
    int updateOrderState( int orderId);
    Boolean deleteOrder(int orderId);
}
