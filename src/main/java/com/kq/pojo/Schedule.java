package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Schedule {//排班
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;
    private String startTime;
    private String endTime;
    private Integer maxReservations = 15; // 最大预约数
    private Integer currentReservations = 0; // 当前预约数
    private String status;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "reservation",
            orphanRemoval = true
    )
    private List<Reservation> reservations;
}
