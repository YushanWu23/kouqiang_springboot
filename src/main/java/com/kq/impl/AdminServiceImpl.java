package com.kq.impl;

import com.kq.SpecialService.EmailService;
import com.kq.dao.IAdminDao;
import com.kq.dao.IDoctorDao;
import com.kq.dao.IMedicalRecordDao;
import com.kq.pojo.Admin;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.service.IAdminService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IAdminDao iAdminDao;
    @Resource
    private IMedicalRecordDao iMedicalRecordDao;
    @Resource
    private IDoctorDao iDoctorDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Admin login(String adminId, String password) {
        return iAdminDao.findAdminByAdminIdAndPassword(adminId, password);
    }
    private boolean isValidId(String adminId) {
        // 假设手机号格式为11位数字，邮箱格式为 [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
        return adminId.matches("\\d{11}") || adminId.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
    @Override
    public int sendForgetPasswordEmailCode(String adminId) {
        // 验证邮箱是否已注册
        if (iAdminDao.countByAdminId(adminId) == 0) {
            throw new IllegalArgumentException("邮箱未注册");
        }
        // 验证邮箱是否有效
        if (!isValidId(adminId)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        // 发送邮件验证码
        String code = generateRandomCode(); // 生成随机验证码
        String emailSubject = "重置密码验证码";
        String emailText = "您的验证码是：" + code;
        if (!emailService.sendEmail(adminId, emailSubject, emailText)) {
            throw new RuntimeException("邮件发送失败");
        }
        // 存储验证码到 Redis 或数据库，设置过期时间
        redisTemplate.opsForValue().set("email:code:" + adminId, code, 5, TimeUnit.MINUTES);
        return 1;
    }
    @Override
    public int passwordForget(String adminId, String newPassword, String emailCode){
        if (iAdminDao.countByAdminId(adminId) == 0) {
            throw new IllegalArgumentException("不存在");
        }
        // 验证邮箱是否有效
        if (!isValidId(adminId)) {
            throw new IllegalArgumentException("格式不正确");
        }
        // 验证邮件验证码是否正确
        String storedCode = redisTemplate.opsForValue().get("email:code:" + adminId);
        if (storedCode == null || !storedCode.equals(emailCode)) {
            throw new IllegalArgumentException("邮件验证码错误或已过期");
        }
        Admin admin = iAdminDao.findAdminByAdminId(adminId);
        admin.setPassword(newPassword);
        iAdminDao.save(admin);
        return 1;
    }

    @Override
    public List<Doctor> getAllDoctors (){
        return iDoctorDao.findAll();
    }
}
