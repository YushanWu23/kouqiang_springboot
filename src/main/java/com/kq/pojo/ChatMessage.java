package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage {//聊天消息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatMessageId;
    private String content;
    private LocalDateTime sendTime;
    private String type;//文本/图片文件
    private String sender;//user、doctor
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="consultationId",nullable = false,referencedColumnName = "consultationId")
    private Consultation consultation;
}
