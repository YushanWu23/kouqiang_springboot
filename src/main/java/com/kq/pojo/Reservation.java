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
    private String status;// 状态(待确认/已预约/已完成/取消)
    private String bookTime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="doctorId",nullable = false,referencedColumnName = "doctorId")
    private Doctor doctor;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="scheduleId",nullable = false,referencedColumnName = "scheduleId")
    private Schedule schedule;
}
