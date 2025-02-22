package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Slf4j
public class Admin {
    @Id
    private String adminId;
    @JsonIgnore
    private String password;
    private String adminName;
    private String adminAddress;
    private String adminExplain;
    //@Column(columnDefinition = "mediumtext")
    //private String adminImg;

}
