package com.kq.dao;

import com.kq.pojo.OrderDetailet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IOrderDetailetDao extends JpaRepository<OrderDetailet,Integer> {
    List<OrderDetailet> findOrderDetailetsByOrdersOrderId(int orderId);
}
