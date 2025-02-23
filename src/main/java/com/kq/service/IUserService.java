package com.kq.service;

import com.kq.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface IUserService {
    User login(String userId,String pwd);
    int register(String userId,String password,String userName,int userSex);
    User getUserInfo(String userId);
    int passwordForget(String userId, String newPassword);
    int updateUserInfo ( String userId,  String userName,  int userSex);
    }
