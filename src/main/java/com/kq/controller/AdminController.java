package com.kq.controller;

import com.kq.pojo.Admin;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.service.IAdminService;
import com.kq.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {
    @Resource
    private IAdminService iAdminService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/login")
    public String login(@RequestParam String adminId,@RequestParam String password) {
        return iAdminService.login(adminId, password)!=null? jwtTokenUtil.generateToken(adminId) :null;
    }
    @PostMapping("/passwordForget")     //忘记密码和修改密码共用
    int passwordForget(@RequestParam String adminId, @RequestParam String newPassword,@RequestParam String emailCode){
        return iAdminService.passwordForget(adminId,newPassword,emailCode);
    }
    @PostMapping("/sendForgetPasswordEmailCode")
    int sendForgetPasswordEmailCode(@RequestParam String adminId){
        return iAdminService.sendForgetPasswordEmailCode(adminId);
    }

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors (){
        return iAdminService.getAllDoctors();
    }
}
