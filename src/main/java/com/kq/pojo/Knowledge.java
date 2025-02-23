package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Knowledge {//科普知识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer knowledgeId;
    @Column(columnDefinition = "mediumtext")
    private String knowledgeExplain;

    private String knowledgeTitle;//标题
}
