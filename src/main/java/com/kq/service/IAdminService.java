package com.kq.service;

import com.kq.pojo.Admin;
import com.kq.pojo.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface IAdminService {
    Admin login(String adminId,String password);
}
