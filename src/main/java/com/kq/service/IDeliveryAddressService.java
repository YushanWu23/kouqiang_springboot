package com.kq.service;

import com.kq.pojo.DeliveryAddress;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IDeliveryAddressService {
    List<DeliveryAddress> getDeliveryAddressByUserId(String userId);
    DeliveryAddress getDeliveryAddressByDaId(int daId);
    int saveDeliveryAddress(String contactName,int contactSex,String contactTel,String address,String userId);
    int updateDeliveryAddress(int daId,String contactName,int contactSex,String contactTel,String address);
    int removeDeliveryAddress(int daId,String userId);
}
