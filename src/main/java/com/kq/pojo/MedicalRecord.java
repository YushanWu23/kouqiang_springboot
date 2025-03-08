package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class MedicalRecord {//病历
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalRecordId;
    private LocalDateTime visitTime;
    private String diagnosis; // 诊断结果
    private String treatmentPlan; // 治疗方案
    @ElementCollection
    @CollectionTable(name = "medicalRecord_images", joinColumns = @JoinColumn(name = "medicalRecord_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;
    @ElementCollection
    @CollectionTable(name = "medicalRecord_images", joinColumns = @JoinColumn(name = "medicalRecord_id"))
    @Column(name = "medicalRecord_url")
    private List<String> medicalRecordUrls;
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
}
