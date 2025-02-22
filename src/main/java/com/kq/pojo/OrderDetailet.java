package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetailet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer odId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "orderId")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name="foodId",referencedColumnName = "foodId")
    private Food food;
    private int quantity;

}
