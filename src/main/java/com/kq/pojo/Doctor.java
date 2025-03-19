package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Entity
@Slf4j
public class Doctor {
    @Id
    private String doctorId;
    @JsonIgnore
    private String password;
    private String doctorName;
    private String specialty; // 擅长领域
    private String title;//职称
    private String doctorImg;
    private int doctorSex;
    private boolean onlineStatus=false;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "doctor",
            orphanRemoval = true
    )
    private List<Schedule> schedules;
}
