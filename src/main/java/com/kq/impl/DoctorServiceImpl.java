package com.kq.impl;

import com.kq.SpecialService.EmailService;
import com.kq.dao.*;
import com.kq.pojo.*;
import com.kq.service.IDoctorService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Join;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Component
public class DoctorServiceImpl implements IDoctorService {
    @Resource
    IDoctorDao iDoctorDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IMedicalRecordDao iMedicalRecordDao;
    @Resource
    IReservationDao iReservationDao;
    @Resource
    IScheduleDao iScheduleDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate redisTemplate;
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
    public List<Doctor> getBySpecialty(String specialty){
        return iDoctorDao.findDoctorsBySpecialty(specialty);
    }
    @Override
    public Doctor login(String doctorId, String pwd) {
        return iDoctorDao.findDoctorByDoctorIdAndPassword(doctorId, pwd);
    }
    private boolean isValidDoctorId(String doctorId) {
        // 假设手机号格式为11位数字，邮箱格式为 [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
        return doctorId.matches("\\d{11}") || doctorId.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
    @Override
    public int sendForgetPasswordEmailCode(String doctorId) {
        // 验证邮箱是否已注册
        if (iDoctorDao.countByDoctorId(doctorId) == 0) {
            throw new IllegalArgumentException("邮箱未注册");
        }
        // 验证邮箱是否有效
        if (!isValidDoctorId(doctorId)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        // 发送邮件验证码
        String code = generateRandomCode(); // 生成随机验证码
        String emailSubject = "重置密码验证码";
        String emailText = "您的验证码是：" + code;
        if (!emailService.sendEmail(doctorId, emailSubject, emailText)) {
            throw new RuntimeException("邮件发送失败");
        }
        // 存储验证码到 Redis 或数据库，设置过期时间
        redisTemplate.opsForValue().set("email:code:" + doctorId, code, 5, TimeUnit.MINUTES);
        return 1;
    }
    @Override
    public int passwordForget(String doctorId, String newPassword, String emailCode){
        // 验证 doctorId 是否存在
        if (iDoctorDao.countByDoctorId(doctorId) == 0) {
            throw new IllegalArgumentException("不存在");
        }
        // 验证邮箱是否有效
        if (!isValidDoctorId(doctorId)) {
            throw new IllegalArgumentException("格式不正确");
        }
        // 验证邮件验证码是否正确
        String storedCode = redisTemplate.opsForValue().get("email:code:" + doctorId);
        if (storedCode == null || !storedCode.equals(emailCode)) {
            throw new IllegalArgumentException("邮件验证码错误或已过期");
        }
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        doctor.setPassword(newPassword);
        iDoctorDao.save(doctor);
        return 1;
    }
    @Override
    public int updateDoctorInfo(String doctorId, String doctorName,  int doctorSex,  String doctorImg,  String specialty,  String title ){
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        doctor.setDoctorName(doctorName);
        doctor.setDoctorSex(doctorSex);
        doctor.setDoctorImg(doctorImg);
        doctor.setSpecialty(specialty);
        doctor.setTitle(title);
        iDoctorDao.save(doctor);
        return 1;
    }
    @Override
    public int updateOnlineStatus(String doctorId,boolean onlineStatus){
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        doctor.setOnlineStatus(onlineStatus);
        iDoctorDao.save(doctor);
        return 1;
    }
    @Override
    public List<Schedule> getScheduleByDoctorId(String doctorId, String startDate, String endDate){
        // 日期格式转换
        LocalDateTime start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE)
                .atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)
                .atTime(LocalTime.MAX);

        return iScheduleDao.findByDoctorDoctorIdAndStartTimeBetweenOrderByStartTimeAsc(
                doctorId,
                start,
                end
        );
    }


}
