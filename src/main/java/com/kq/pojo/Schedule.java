package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kq.pojo.forUser.DeliveryAddress;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Schedule {//排班
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
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
    @ManyToOne
    @JoinColumn(
            name = "doctorId",
            referencedColumnName = "doctorId",
            nullable = false
    )
    private Doctor doctor;
}
