package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Consultation {//咨询记录
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consultationId;
    private String startTime;
    private String endTime;
    @ManyToOne
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId",
            nullable = false
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "doctorId",
            referencedColumnName = "doctorId",
            nullable = false
    )
    private Doctor doctor;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "chatMessage",
            orphanRemoval = true
    )
    private List<ChatMessage> chatMessages;
}
