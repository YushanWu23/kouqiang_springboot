package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reservation {//预约
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    @Column(columnDefinition = "mediumtext")
    private String reservationExplain;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;

}
