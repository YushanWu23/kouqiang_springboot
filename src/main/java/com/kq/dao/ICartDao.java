package com.kq.dao;
import com.kq.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDao extends JpaRepository<Cart,Integer> {
    List<Cart> findCartsByUserUserId(String userId);

    Cart findCartsByUserUserIdAndFoodFoodId(String userId,int foodId);
}
