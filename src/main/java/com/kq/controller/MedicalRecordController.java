package com.kq.controller;

import com.kq.pojo.MedicalRecord;
import com.kq.service.IMedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/searchMedicalRecords")
    public List<MedicalRecord> searchMedicalRecords (@RequestParam(value = "doctorId",required = false) String doctorId,
                                                     @RequestParam(value = "userId",required = false) String userId,
                                                     @RequestParam(value = "date",required = false) LocalDateTime date){
        return iMedicalRecordService.searchMedicalRecords (doctorId, userId, date);
    }
}
