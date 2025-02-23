package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bookmark {//科普知识的收藏夹，和购物车功能类似
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookmarkId;
    @ManyToOne
    @JoinColumn(name = "knowledgeId",
            referencedColumnName = "knowledgeId",
            nullable = false
    )
    private Knowledge knowledge;
    @ManyToOne
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId",
            nullable = false
    )
    @JsonIgnore
    private User user;
}
