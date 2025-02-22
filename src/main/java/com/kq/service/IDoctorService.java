package com.kq.service;

import com.kq.pojo.unuse.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IDoctorService {
    List<Doctor> getByDoctorNameContaining(String doctorName);
    List<Doctor> getAll();
    Doctor getByDoctorId(String doctorId);
    List<Doctor> getByOrderTypeId(int orderTypeId);
    Doctor login(String doctorId, String pwd);
    int register(String doctorId,String password,String doctorName,int doctorSex);
    int getDoctorInfo(String doctorId);
}
