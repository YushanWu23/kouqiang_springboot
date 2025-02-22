package com.kq.impl;

import com.kq.dao.IUserDao;
import com.kq.pojo.User;
import com.kq.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao iUserDao;
    @Override
    public User login(String userId, String pwd) {
        return iUserDao.findUserByUserIdAndPassword(userId, pwd);
    }
    @Override
    public int register(String userId,String password,String userName,int userSex) {
        // 验证 userId 是否已存在
        if (iUserDao.countByUserId(userId) > 0) {
            throw new IllegalArgumentException("用户ID已存在");
        }

        // 验证 userId 是否是有效的手机号或邮箱
        if (!isValidUserId(userId)) {
            throw new IllegalArgumentException("用户ID格式不正确");
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
    public boolean passwordForget(String userId, String newPassword){
        // 验证 userId 是否存在
        if (iUserDao.countByUserId(userId) == 0) {
            throw new IllegalArgumentException("用户ID不存在");
        }

        // 更新密码
        int updatedRows = iUserDao.updatePasswordByUserId(newPassword, userId);
        return updatedRows > 0;
    }

    // 验证 userId 是否是有效的手机号或邮箱
    private boolean isValidUserId(String userId) {
        // 假设手机号格式为11位数字，邮箱格式为 [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
        return userId.matches("\\d{11}") || userId.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
