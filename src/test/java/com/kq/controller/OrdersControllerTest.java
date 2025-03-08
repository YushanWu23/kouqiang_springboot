package com.kq.controller;
import com.kq.controller.forUser.OrdersController;
import com.kq.pojo.forUser.Orders;
import com.kq.pojo.*;
import com.kq.pojo.Doctor;
import com.kq.pojo.forUser.DeliveryAddress;
import com.kq.service.forUser.IOrdersService;
import com.kq.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersController.class)
@Import(JwtTokenUtil.class) // 确保 JwtTokenUtil 被包含在测试上下文中
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrdersService iOrdersService;

    private Orders sampleOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User sampleUser = new User();
        sampleUser.setUserId("123");
        sampleUser.setUserName("testUser");

        Doctor sampleBusiness = new Doctor();
        sampleBusiness.setDoctorId("a");
        sampleBusiness.setDoctorName("testBusiness");

        DeliveryAddress sampleAddress = new DeliveryAddress();
        sampleAddress.setDaId(1);
        sampleAddress.setContactName("testContact");

        sampleOrder = new Orders();
        sampleOrder.setOrderId(1);
        sampleOrder.setUser(sampleUser);
        //sampleOrder.setDoctor(sampleBusiness);
        sampleOrder.setDeliveryAddress(sampleAddress);
        sampleOrder.setOrderTotal(100.0);
        // Initialize other fields as needed
    }

    @Test
    public void testGetOrdersByOrdersId() throws Exception {
        //when(iOrdersService.getOrdersByOrdersId(anyInt())).thenReturn(sampleOrder);

        mockMvc.perform(get("/orders/getOrdersByOrdersId")
                        .param("orderId", "16"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrdersByUserId() throws Exception {
        //when(iOrdersService.getOrdersByUserId(anyString())).thenReturn(Arrays.asList(sampleOrder));

        mockMvc.perform(get("/orders/getOrdersByUserId")
                        .param("userId", "123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrderState() throws Exception {
        //when(iOrdersService.updateOrderState(anyInt())).thenReturn(1);

        mockMvc.perform(get("/orders/updateOrderState")
                        .param("orderId", "16"))
                .andExpect(status().isOk());
    }
}