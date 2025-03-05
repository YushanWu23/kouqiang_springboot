package com.kq.pojo;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Data
public class Feedback {//反馈
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    @Column(columnDefinition = "mediumtext")
    private String feedbackExplain;

    @ElementCollection
    @CollectionTable(name = "feedback_images", joinColumns = @JoinColumn(name = "feedback_id"))
    @Column(name = "image_url")
    private List<String> imageUrls; // 存储多张图片的 URL
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId",nullable = false,referencedColumnName = "userId")
    private User user;
}
