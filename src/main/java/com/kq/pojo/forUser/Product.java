package com.kq.pojo.forUser;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {//商品
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productImg;
    private Double productPrice;
}
