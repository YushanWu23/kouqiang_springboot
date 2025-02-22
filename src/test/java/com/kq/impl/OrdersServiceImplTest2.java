package com.kq.impl;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class OrdersServiceImplTest2 {
   /* @Resource
    OrdersServiceImpl ordersService;

    *//*@Test
    void testCreateOrders() {
        // 模拟数据
        String userId = "123";
        int businessId = 10001;
        int daId = 2;
        Double orderTotal = 100.0;

        // 调用待测试的方法
        int createdOrderId = ordersService.createOrders(userId, businessId, daId, orderTotal);
        System.out.println(createdOrderId);
        // 验证订单是否被创建并返回了订单ID
        assertEquals(56, createdOrderId);
    }*//*
    @Test
    void testGetOrdersByOrdersId() {
        // 模拟数据
        int orderId = 16;

        // 调用待测试的方法
        Orders orders = ordersService.getOrdersByOrdersId(orderId);
        System.out.println(orders);
    }
    @Test
    void testGetOrdersByUserId() {
        // 模拟数据
        String userId = "123";

        // 调用待测试的方法
        List<Orders> orders = ordersService.getOrdersByUserId(userId);
        System.out.println(orders);
    }
    @Test
    void testUpdateOrderState() {
        // 模拟数据
        int orderId = 53;

        // 调用待测试的方法
        int result = ordersService.updateOrderState(orderId);
        System.out.println(result);
        assertEquals(1, result);
    }
//    @Test
//    void testGetOrdersByOrdersId() {
//        int orderId = 16;
//        Orders expectedOrders = new Orders();
//        expectedOrders.setOrderId(orderId);
//        // 模拟DAO方法的行为
//        when(mockOrdersDao.findOrdersByOrderId(orderId)).thenReturn(expectedOrders);
//        Orders result = ordersService.getOrdersByOrdersId(orderId);
//        // 验证
//        assertEquals(expectedOrders.getOrderId(), result.getOrderId());
//    }
//
//    @Test
//    void testGetOrdersByUserId() {
//        String userId = "123";
//        Orders order1 = new Orders();
//        Orders order2 = new Orders();
//        List<Orders> expectedOrders = Arrays.asList(order1, order2);
//
//        when(mockOrdersDao.findOrdersByUserUserId(userId)).thenReturn(expectedOrders);
//        List<Orders> result = ordersService.getOrdersByUserId(userId);
//        assertEquals(expectedOrders.size(), result.size());
//        assertEquals(expectedOrders.get(0), result.get(0));
//        assertEquals(expectedOrders.get(1), result.get(1));
//    }

//    @Test
//    void testUpdateOrderState() {
//        int orderId = 47;
//        Orders order = new Orders();
//        order.setOrderId(orderId);
//        order.setState(0); // Initial state
//
//        when(mockOrdersDao.findOrdersByOrderId(orderId)).thenReturn(order);
//        order.setState(1); // Updated state
//        when(mockOrdersDao.save(any(Orders.class))).thenReturn(order);
//
//        int updatedOrderState = ordersService.updateOrderState(orderId);
//
//        // 验证订单状态是否已更新并保存
//        assertEquals(1, updatedOrderState);
//        verify(mockOrdersDao).save(order); // 验证保存方法是否被调用，并且传入了更新后的订单对象
//    }*/
}


