package com.kq.pojo;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
public class Note {//记录
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;
    @Column(columnDefinition = "mediumtext")
    private String noteExplain;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;
}
