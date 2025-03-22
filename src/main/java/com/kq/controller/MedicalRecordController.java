package com.kq.controller;

import com.kq.pojo.MedicalRecord;
import com.kq.service.IMedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Resource
    IMedicalRecordService iMedicalRecordService;
    @PostMapping("/createMedicalRecord")//创建病历
    MedicalRecord createMedicalRecord (@RequestParam String doctorId, @RequestParam String userId, @RequestParam String diagnosis, @RequestParam String treatmentPlan, @RequestParam(value = "files",required = false) MultipartFile[] files){
        return iMedicalRecordService.createMedicalRecord (doctorId,userId,diagnosis,treatmentPlan,files);
    }
    @GetMapping("/getMedicalRecordByUserId")
    List<MedicalRecord> getMedicalRecordByUserId (@RequestParam String userId){
        return iMedicalRecordService.getMedicalRecordByUserId(userId);
    }
    @GetMapping("/getAllMedicalRecord")
    public List<MedicalRecord> getAllMedicalRecord (){
        return iMedicalRecordService.getAllMedicalRecord ();
    }
    @GetMapping("/getMedicalRecordById")
    MedicalRecord getMedicalRecordById (@RequestParam int medicalRecordId){
        return iMedicalRecordService.getMedicalRecordById(medicalRecordId);
    }
    @GetMapping("/searchMedicalRecords")
    public List<MedicalRecord> searchMedicalRecords (@RequestParam(value = "doctorId",required = false) String doctorId,
                                                     @RequestParam(value = "userId",required = false) String userId,
                                                     @RequestParam(value = "date",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return iMedicalRecordService.searchMedicalRecords (doctorId, userId, date);
    }
}
