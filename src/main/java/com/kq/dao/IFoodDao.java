package com.kq.dao;

import com.kq.pojo.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IFoodDao extends JpaRepository<Food,Integer> {
    Food findFoodByFoodId(int foodId);
}
