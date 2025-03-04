package com.kq.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {//商品
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    @Column(columnDefinition = "mediumtext")
    private String productImg;
    private Double productPrice;
}
