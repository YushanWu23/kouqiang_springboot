package com.kq.impl;

import com.kq.dao.IAdminDao;
import com.kq.pojo.Admin;
import com.kq.service.IAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IAdminDao iAdminDao;
    @Override
    public Admin login(String adminId, String password) {
        return iAdminDao.findAdminByAdminIdAndPassword(adminId, password);
    }
}
