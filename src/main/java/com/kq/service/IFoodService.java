package com.kq.service;

import com.kq.pojo.Food;
import com.kq.pojo.unuse.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IFoodService {
   Food getByFoodId( int foodId);
    List<Food> getAll();
}
