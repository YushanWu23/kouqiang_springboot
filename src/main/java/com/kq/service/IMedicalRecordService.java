package com.kq.service;

import com.kq.pojo.MedicalRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IMedicalRecordService {
    MedicalRecord createMedicalRecord(String doctorId, String userId, String diagnosis, String treatmentPlan, MultipartFile[] files);
    List<MedicalRecord> getMedicalRecordByUserId(String userId);
    List<MedicalRecord> getAllMedicalRecord ();
    MedicalRecord getMedicalRecordById ( int medicalRecordId);
    List<MedicalRecord> searchMedicalRecords (String doctorId, String userId, LocalDate date);
}
