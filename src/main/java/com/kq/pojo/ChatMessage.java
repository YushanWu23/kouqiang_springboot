package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChatMessage {//聊天消息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatMessageId;
    private String content;
    private String sendTime;
    private String type;//文本/图片文件
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="consultationId",nullable = false,referencedColumnName = "consultationId")
    private Consultation consultation;
}
