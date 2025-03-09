package com.kq.impl;

import com.kq.dao.IAdminDao;
import com.kq.dao.IDoctorDao;
import com.kq.dao.IMedicalRecordDao;
import com.kq.pojo.Admin;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.service.IAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IAdminDao iAdminDao;
    @Resource
    private IMedicalRecordDao iMedicalRecordDao;
    @Resource
    private IDoctorDao iDoctorDao;
    @Override
    public Admin login(String adminId, String password) {
        return iAdminDao.findAdminByAdminIdAndPassword(adminId, password);
    }
    @Override
    public List<MedicalRecord> getAllMedicalRecord (){
        return iMedicalRecordDao.findAll();
    }
    @Override
    public List<MedicalRecord> searchMedicalRecords(String doctorId, String userId, LocalDateTime date) {
        return iMedicalRecordDao.findAll().stream()
                .filter(record -> (doctorId == null || doctorId.equals(record.getDoctor().getDoctorId())))
                .filter(record -> (userId == null || userId.equals(record.getUser().getUserId())))
                .filter(record -> (date == null || record.getVisitTime().toLocalDate().equals(date.toLocalDate())))
                .collect(Collectors.toList());
    }
    @Override
    public List<Doctor> getAllDoctors (){
        return iDoctorDao.findAll();
    }
}
