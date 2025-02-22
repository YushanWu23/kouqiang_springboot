package com.kq.impl;

import com.kq.dao.IFoodDao;
import com.kq.pojo.Food;
import com.kq.service.IFoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FoodServiceImpl implements IFoodService {
    @Resource
    IFoodDao iFoodDao;
    @Override
    public Food getByFoodId(int foodId){
        return iFoodDao.findFoodByFoodId(foodId);
    }
    @Override
    public List<Food> getAll(){
        return iFoodDao.findAll();
    }
}
