package com.kq.pojo.unuse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Slf4j
public class Doctor {
    @Id
    private String doctorId;
    @JsonIgnore
    private String password;
    private String doctorName;
    private String doctorAddress;
    private String doctorExplain;
    @Column(columnDefinition = "mediumtext")
    private String doctorImg;

    private int doctorSex;
    /*private int orderTypeId;
    private Double starPrice = 0.0;
    private Double deliveryPrice = 0.0;
    private String remarks;
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "business")
    private List<Food> food = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "business",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Orders> order = new ArrayList<>();*/

}
