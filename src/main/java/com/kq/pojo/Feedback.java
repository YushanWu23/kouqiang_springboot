package com.kq.pojo;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
public class Feedback {//反馈
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    @Column(columnDefinition = "mediumtext")
    private String feedbackExplain;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;
}
