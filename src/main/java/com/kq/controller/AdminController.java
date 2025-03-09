package com.kq.controller;

import com.kq.pojo.Admin;
import com.kq.service.IAdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {
    @Resource
    private IAdminService iAdminService;
    @PostMapping("/login")
    public Admin login(String adminId, String password) {
        return iAdminService.login(adminId, password);
    }
}
