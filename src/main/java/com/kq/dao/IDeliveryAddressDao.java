package com.kq.dao;

import com.kq.pojo.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDeliveryAddressDao extends JpaRepository<DeliveryAddress,Integer> {
    List<DeliveryAddress> findDeliveryAddressesByUserUserId(String userId);
    DeliveryAddress findDeliveryAddressByDaId(int daId);
}
