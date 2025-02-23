package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.pojo.unuse.Doctor;
import com.kq.service.IDoctorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DoctorServiceImpl implements IDoctorService {
    /*@Resource
    IDoctorDao iDoctorDao;
    @Override
    public Doctor getByDoctorId(String doctorId){
        return iDoctorDao.findDoctorByDoctorId(doctorId);
    }
    @Override
    public List<Doctor> getByDoctorNameContaining(String doctorName){
        return  iDoctorDao.findDoctorsByDoctorNameContaining(doctorName);
    }
    @Override
    public List<Doctor> getAll(){
        return iDoctorDao.findAll();
    }
    @Override
    public List<Doctor> getByOrderTypeId(int orderTypeId){
        return iDoctorDao.findDoctorsByOrderTypeId(orderTypeId);
    }
    @Override
    public Doctor login(String doctorId, String pwd) {
        return iDoctorDao.findDoctorByDoctorIdAndPassword(doctorId, pwd);
    }
    @Override
    public int register(String doctorId,String password,String doctorName,int doctorSex) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setPassword(password);
        doctor.setDoctorName(doctorName);
        doctor.setDoctorSex(doctorSex);
        iDoctorDao.save(doctor);
        return 1;
    }
    @Override//根据用户编号查询用户表返回的行数
    public int getDoctorInfo(String doctorId) {
        long count = iDoctorDao.countByDoctorId(doctorId);
        return (int) count;
    }*/
}
