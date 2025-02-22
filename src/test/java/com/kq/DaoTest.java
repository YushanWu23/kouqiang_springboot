/*
package com.elm;

import com.elm.dao.IBusinessDao;
import com.elm.pojo.Business;
import com.elm.pojo.Food;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DaoTest {
    @Resource
    public IBusinessDao busDao;

    @Test
    @Transactional
    @Commit
    public void insertBusiness(){
        Business bus = new Business();
        bus.setBusinessId(2);
        bus.setBusinessName("名字2");
        bus.setBusinessAddress("地址2");
        bus.setBusinessExplain("js2");
        bus.setDeliveryPrice(10.0);
        Food food = new Food();
        food.setFoodId(1);
        food.setFoodName("鸡腿堡");
        food.setFoodPrice(15.0);
        bus.getFood().add(food);
        busDao.save(bus);
    }
    @Test
    @Transactional
    @Commit
    public void deleteBusiness(){
        busDao.deleteById(2);
    }
    @Test
    @Transactional
    @Commit
    public void updateBusiness(){
        Optional<Business> bus = busDao.findById(1);
        if(bus.isPresent()){
            bus.get().setDeliveryPrice(15.8);
            busDao.save(bus.get());
        }
    }
    @Test
    public void findBusiness(){
        Optional<Business> busInfo = busDao.findById(2);
        if(busInfo.isPresent()){
            Business buss = busInfo.get();

        }
    }
    @Test
    public void findBusinessByNameAndAddress(){
        List<Business> busList = busDao.findBusinessesByBusinessNameContainingAndBusinessAddressContaining("名字","地址");
        System.out.println("");

    }

}
*/
