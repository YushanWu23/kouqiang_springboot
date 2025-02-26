package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Food {//商品
    @Id
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    @Column(columnDefinition = "mediumtext")
    private String foodImg;
    private Double foodPrice;
    private String remarks;
}
