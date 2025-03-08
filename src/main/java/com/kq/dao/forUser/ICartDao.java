package com.kq.dao.forUser;
import com.kq.pojo.forUser.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDao extends JpaRepository<Cart,Integer> {
    List<Cart> findCartsByUserUserId(String userId);

    Cart findCartsByUserUserIdAndProductProductId(String userId,int productId);
}
