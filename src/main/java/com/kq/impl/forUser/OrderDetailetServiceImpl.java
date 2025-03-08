package com.kq.impl.forUser;

import com.kq.dao.forUser.IOrderDetailetDao;
import com.kq.pojo.forUser.OrderDetailet;
import com.kq.service.forUser.IOrderDetailetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderDetailetServiceImpl implements IOrderDetailetService {
    @Resource
    IOrderDetailetDao iOrderDetailetDao;
    @Override
    public List<OrderDetailet> getByOrderId(int orderId){
        return iOrderDetailetDao.findOrderDetailetsByOrdersOrderId(orderId);
    }
    @Override
    public int saveOrderDetailetBatch(List<OrderDetailet> list){
        List<OrderDetailet> savedList = iOrderDetailetDao.saveAll(list);
        return savedList.size();// // 返回保存的行数
    }
}
