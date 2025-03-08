package com.kq.impl.forUser;
import com.kq.dao.*;
import com.kq.dao.forUser.ICartDao;
import com.kq.dao.forUser.IDeliveryAddressDao;
import com.kq.dao.forUser.IProductDao;
import com.kq.dao.forUser.IOrdersDao;
import com.kq.pojo.*;
import com.kq.pojo.forUser.Cart;
import com.kq.pojo.forUser.DeliveryAddress;
import com.kq.pojo.forUser.OrderDetailet;
import com.kq.pojo.forUser.Orders;
import com.kq.service.forUser.IOrdersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersServiceImpl implements IOrdersService {
    @Resource
    IOrdersDao iOrdersDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IProductDao iProductDao;
    @Resource
    IDeliveryAddressDao iDeliveryAddressDao;
    @Resource
    ICartDao iCartDao;
    @Override
    @Transactional
    public Orders createOrders(String userId, int daId, Double orderTotal) {
        Orders orders = new Orders();
        orders.setOrderTotal(orderTotal);
        User user = iUserDao.findUserByUserId(userId);
        DeliveryAddress deliveryAddress = iDeliveryAddressDao.findDeliveryAddressByDaId(daId);
        orders.setDeliveryAddress(deliveryAddress);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders);
        user.setOrders(ordersList);
        user.addOrders(orders);
        user.getOrders().add(orders);
        orders.setUser(user);

        List<Cart> carts = iCartDao.findCartsByUserUserId(userId);
        for (Cart cart: carts){
            OrderDetailet orderDetailet = new OrderDetailet();
            orderDetailet.setProduct(cart.getProduct());
            orderDetailet.setQuantity(cart.getQuantity());
            orders.addOrderDetail(orderDetailet);
            user.removeCarts(cart);
        }
        iUserDao.save(user);
        System.out.println(orders.getOrderId());
        return orders;
    }
    @Override
    public Orders getOrdersByOrdersId(int orderId){
        Orders orders = iOrdersDao.findOrdersByOrderId(orderId);
        System.out.println(orders.getOrderId());
        return orders;
    }
    @Override
    public List<Orders> getOrdersByUserId(String userId){
        return iOrdersDao.findOrdersByUserUserId(userId);
    }
    @Override
    public int updateOrderState( int orderId){
        Orders orders = iOrdersDao.findOrdersByOrderId(orderId);
        orders.setOrderState(1);
        iOrdersDao.save(orders);
        return orders.getOrderState();
    }
}
