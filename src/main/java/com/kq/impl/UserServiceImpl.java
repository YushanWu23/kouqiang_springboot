package com.kq.impl;

import com.kq.dao.IUserDao;
import com.kq.pojo.User;
import com.kq.service.EmailService;
import com.kq.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;
import java.util.Random;

@Component
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao iUserDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public User login(String userId, String pwd) {
        return iUserDao.findUserByUserIdAndPassword(userId, pwd);
    }
    @Override
    public int sendRegisterEmailCode(String userId) {
        // 验证邮箱是否已注册
        if (iUserDao.countByUserId(userId) > 0) {
            throw new IllegalArgumentException("邮箱已注册");
        }
        // 验证邮箱是否有效
        if (!isValidUserId(userId)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        // 发送邮件验证码
        String code = generateRandomCode(); // 生成随机验证码
        String emailSubject = "注册验证码";
        String emailText = "您的验证码是：" + code;
        if (!emailService.sendEmail(userId, emailSubject, emailText)) {
            throw new RuntimeException("邮件发送失败");
        }
        // 存储验证码到 Redis 或数据库，设置过期时间
        redisTemplate.opsForValue().set("email:code:" + userId, code, 5, TimeUnit.MINUTES);
        return 1;
    }
    @Override
    public int register(String userId,String password,String userName,int userSex,String emailCode) {
        // 验证 userId 是否已存在
        if (iUserDao.countByUserId(userId) > 0) {
            throw new IllegalArgumentException("已存在");
        }
        // 验证 userId 是否是有效的手机号或邮箱
        if (!isValidUserId(userId)) {
            throw new IllegalArgumentException("格式不正确");
        }
        // 验证邮箱验证码是否正确
        String storedCode = redisTemplate.opsForValue().get("email:code:" + userId);
        if (storedCode == null || !storedCode.equals(emailCode)) {
            throw new IllegalArgumentException("邮箱验证码错误或已过期");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserSex(userSex);
        iUserDao.save(user);
        return 1;
    }
    @Override//根据用户编号查询用户表返回的行数
    public User getUserInfo(String userId) {
        return iUserDao.findUserByUserId(userId);
    }
    @Override
    public int sendForgetPasswordEmailCode(String userId) {
        // 验证邮箱是否已注册
        if (iUserDao.countByUserId(userId) == 0) {
            throw new IllegalArgumentException("邮箱未注册");
        }
        // 验证邮箱是否有效
        if (!isValidUserId(userId)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        // 发送邮件验证码
        String code = generateRandomCode(); // 生成随机验证码
        String emailSubject = "重置密码验证码";
        String emailText = "您的验证码是：" + code;
        if (!emailService.sendEmail(userId, emailSubject, emailText)) {
            throw new RuntimeException("邮件发送失败");
        }
        // 存储验证码到 Redis 或数据库，设置过期时间
        redisTemplate.opsForValue().set("email:code:" + userId, code, 5, TimeUnit.MINUTES);
        return 1;
    }
    @Override
    public int passwordForget(String userId, String newPassword, String emailCode){
        // 验证 userId 是否存在
        if (iUserDao.countByUserId(userId) == 0) {
            throw new IllegalArgumentException("不存在");
        }
        // 验证邮箱是否有效
        if (!isValidUserId(userId)) {
            throw new IllegalArgumentException("格式不正确");
        }
        // 验证邮件验证码是否正确
        String storedCode = redisTemplate.opsForValue().get("email:code:" + userId);
        if (storedCode == null || !storedCode.equals(emailCode)) {
            throw new IllegalArgumentException("邮件验证码错误或已过期");
        }
        User user = iUserDao.findUserByUserId(userId);
        user.setPassword(newPassword);
        iUserDao.save(user);
        return 1;
    }

    // 验证 userId 是否是有效的手机号或邮箱
    private boolean isValidUserId(String userId) {
        // 假设手机号格式为11位数字，邮箱格式为 [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
        return userId.matches("\\d{11}") || userId.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    @Override
    public int updateUserInfo ( String userId,  String userName,  int userSex){
        User user = iUserDao.findUserByUserId(userId);
        user.setUserName(userName);
        user.setUserSex(userSex);
        iUserDao.save(user);
        return 1;
    }
}
