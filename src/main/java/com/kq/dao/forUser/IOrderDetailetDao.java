package com.kq.dao.forUser;

import com.kq.pojo.forUser.OrderDetailet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IOrderDetailetDao extends JpaRepository<OrderDetailet,Integer> {
    List<OrderDetailet> findOrderDetailetsByOrdersOrderId(int orderId);
}
