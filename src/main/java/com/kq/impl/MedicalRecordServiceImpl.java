package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.dao.IMedicalRecordDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.pojo.User;
import com.kq.service.IMedicalRecordService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MedicalRecordServiceImpl implements IMedicalRecordService {
    @Resource
    IDoctorDao iDoctorDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IMedicalRecordDao iMedicalRecordDao;
    @Transactional
    public MedicalRecord createMedicalRecord(String doctorId, String userId, String diagnosis, String treatmentPlan, MultipartFile[] files){
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        User user = iUserDao.findUserByUserId(userId);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDoctor(doctor);
        medicalRecord.setUser(user);
        medicalRecord.setVisitTime(LocalDateTime.now());
        medicalRecord.setDiagnosis(diagnosis);
        medicalRecord.setTreatmentPlan(treatmentPlan);
        List<String> imageUrls = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 确保上传目录存在
                        Path uploadDir = Paths.get("D:\\kouqiang-uploadImg");
                        if (!Files.exists(uploadDir)) {
                            Files.createDirectories(uploadDir);
                        }

                        // 生成唯一的文件名
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadDir.resolve(fileName);

                        // 保存文件
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // 生成图片访问 URL
                        String imageUrl = "/uploads/" + fileName;
                        imageUrls.add(imageUrl);

                        System.out.println("文件保存成功：" + filePath); // 打印文件路径
                        System.out.println("生成的图片 URL：" + imageUrl); // 打印生成的 URL
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store image file", e);
                    }
                }
            }
        }
        medicalRecord.setImageUrls(imageUrls);
        iMedicalRecordDao.save(medicalRecord);
        return medicalRecord;
    }
    @Override
    public List<MedicalRecord> getMedicalRecordByUserId(String userId){
        return iMedicalRecordDao.getMedicalRecordsByUserUserId(userId);
    }
    @Override
    public List<MedicalRecord> getAllMedicalRecord (){
        return iMedicalRecordDao.findAll();
    }
    @Override
    public List<MedicalRecord> searchMedicalRecords(String doctorId, String userId, LocalDate date) {
        if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return iMedicalRecordDao.findByDoctorDoctorIdAndUserUserIdAndVisitTimeBetween(
                    doctorId, userId, start, end
            );
        }
        return iMedicalRecordDao.findByDoctorDoctorIdAndUserUserId(doctorId, userId);
    }
    @Override
   public MedicalRecord getMedicalRecordById ( int medicalRecordId){
        return iMedicalRecordDao.findMedicalRecordByMedicalRecordId(medicalRecordId);
    }
}
