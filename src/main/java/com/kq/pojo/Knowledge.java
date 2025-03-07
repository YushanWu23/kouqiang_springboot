package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Knowledge {//科普知识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer knowledgeId;
    @Column(columnDefinition = "mediumtext")
    private String knowledgeExplain;
    @ElementCollection
    @CollectionTable(name = "knowledge_images", joinColumns = @JoinColumn(name = "knowledge_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    private String knowledgeTitle;//标题
}
