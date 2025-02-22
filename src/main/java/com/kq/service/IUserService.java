package com.kq.service;

import com.kq.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User login(String userId,String pwd);
    int register(String userId,String password,String userName,int userSex);
    User getUserInfo(String userId);
    boolean passwordForget(String userId, String newPassword);
}
