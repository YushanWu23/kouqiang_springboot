package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @ManyToOne
    @JoinColumn(name = "foodId",
            referencedColumnName = "foodId",
            nullable = false
    )
    private Food food;
    /*@ManyToOne
    @JoinColumn(name = "businessId",
            referencedColumnName = "businessId",
            nullable = false
    )
    private Doctor doctor;*/
    @ManyToOne
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId",
            nullable = false
    )
    @JsonIgnore
    private User user;
    private int quantity;
}
