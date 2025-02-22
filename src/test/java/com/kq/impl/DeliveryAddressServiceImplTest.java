package com.kq.impl;

import com.kq.dao.IUserDao;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeliveryAddressServiceImplTest {
    @Resource
    IUserDao iUserDao;
    @Resource
    DeliveryAddressServiceImpl deliveryAddressService;
    @Test
    public void testSaveDeliveryAddress_Success() {
        String contactName = "John Doe";
        int contactSex = 1;
        String contactTel = "1234567890";
        String address = "123 Main St";
        String userId = "12345";

        int result = deliveryAddressService.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);

        assertEquals(1, result);
    }

    @Test
    public void testSaveDeliveryAddress_MissingParameters() {
        String contactName = "John Doe";
        int contactSex = 1;
        String contactTel = "1234567890";
        String address = "123 Main St";
        String userId = null;

        int result = deliveryAddressService.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);

        assertEquals(0, result);
    }

    @Test
    public void testSaveDeliveryAddress_InvalidUserId() {
        String contactName = "John Doe";
        int contactSex = 1;
        String contactTel = "1234567890";
        String address = "123 Main St";
        String userId = "99999"; // Invalid userId

        int result = deliveryAddressService.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);

        assertEquals(0, result);
    }
}