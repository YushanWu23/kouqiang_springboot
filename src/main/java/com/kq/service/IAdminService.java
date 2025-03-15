package com.kq.service;

import com.kq.pojo.Admin;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.pojo.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IAdminService {
    Admin login(String adminId,String password);
    int passwordForget(String adminId, String newPassword, String emailCode);
    int sendForgetPasswordEmailCode(String adminId);

    List<Doctor> getAllDoctors ();
}

