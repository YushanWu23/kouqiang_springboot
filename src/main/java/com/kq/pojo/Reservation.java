package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {//预约
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    private Integer status = 0;// 状态(0未预约/1已预约/2已完成)
    private LocalDateTime bookTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="doctorId",nullable = false,referencedColumnName = "doctorId")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="scheduleId",nullable = false,referencedColumnName = "scheduleId")
    private Schedule schedule;
}
