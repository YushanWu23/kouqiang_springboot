package com.kq.impl;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kq.dao.*;
import com.kq.dao.IOrdersDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class OrdersServiceImplTest {

    @Mock
    IOrdersDao mockOrdersDao;

    @Mock
    IUserDao mockUserDao;

/*    @Mock
    IBusinessDao mockBusinessDao;

    @Mock
    IDeliveryAddressDao mockDeliveryAddressDao;

    @Mock
    ICartDao mockCartDao;*/

    @InjectMocks
    OrdersServiceImpl ordersService;

/*    @Test
    void testCreateOrders() {
        // 模拟数据
        String userId = "123";
        int businessId = 10001;
        int daId = 2;
        Double orderTotal = 100.0;

        // 模拟User对象
        User mockUser = new User();
        mockUser.setUserId(userId);
        when(mockUserDao.findUserByUserId(userId)).thenReturn(mockUser);

*//*        // 调用待测试的方法
        int createdOrderId = *//*
        Orders result = ordersService.createOrders(userId, businessId, daId, orderTotal);

        // 验证订单是否被创建并返回了 ID
        assertEquals(mockUser.getUserId(), result.getUser().getUserId());
*//*
        // 验证User对象的orders列表中是否添加了Orders对象
//       verify(mockUser).addOrders(any(Orders.class));*//*
    }*/

   /* @Test
    void testGetOrdersByOrdersId() {
        int orderId = 16;
        Orders expectedOrders = new Orders();
        expectedOrders.setOrderId(orderId);
        // 模拟DAO方法的行为
        when(mockOrdersDao.findOrdersByOrderId(orderId)).thenReturn(expectedOrders);
        Orders result = ordersService.getOrdersByOrdersId(orderId);
        // 验证
        assertEquals(expectedOrders.getOrderId(), result.getOrderId());
    }

    @Test
    void testGetOrdersByUserId() {
        String userId = "123";
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        List<Orders> expectedOrders = Arrays.asList(order1, order2);

        when(mockOrdersDao.findOrdersByUserUserId(userId)).thenReturn(expectedOrders);
        List<Orders> result = ordersService.getOrdersByUserId(userId);
        assertEquals(expectedOrders.size(), result.size());
        assertEquals(expectedOrders.get(0), result.get(0));
        assertEquals(expectedOrders.get(1), result.get(1));
    }

    @Test
    void testUpdateOrderState() {
        int orderId = 47;
        Orders order = new Orders();
        order.setOrderId(orderId);

        when(mockOrdersDao.findOrdersByOrderId(orderId)).thenReturn(order);



        int updatedOrderState = ordersService.updateOrderState(orderId);

        // 验证订单状态是否已更新并保存
        assertEquals(1, updatedOrderState);
        verify(mockOrdersDao).save(order); // 验证保存方法是否被调用，并且传入了更新后的订单对象
    }*/
}


