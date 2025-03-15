package com.kq.service;

import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.pojo.Reservation;
import com.kq.pojo.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IDoctorService {
    List<Doctor> getByDoctorNameContaining(String doctorName);
    List<Doctor> getAll();
    Doctor getByDoctorId(String doctorId);
    List<Doctor> getBySpecialty(String specialty);
    Doctor login(String doctorId, String pwd);
    /*int register(String doctorId,String password,String doctorName,int doctorSex);
    */
    int passwordForget(String doctorId, String newPassword, String emailCode);
    int sendForgetPasswordEmailCode(String doctorId);
    int updateDoctorInfo(String doctorId, String doctorName,  int doctorSex,  String doctorImg,  String specialty,  String title );
    /*int getDoctorInfo(String doctorId);*/
    int updateOnlineStatus(String doctorId,boolean onlineStatus);
    List<Schedule> getScheduleByDoctorId(String doctorId,String startDate,String endDate);

}
