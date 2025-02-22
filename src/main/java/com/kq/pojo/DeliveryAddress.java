package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer daId;
    private String contactName;
    private int contactSex;
    private String contactTel;
    private String address;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "userId",nullable = false)
    private User user;
}
