package com.kq.controller;

import com.kq.pojo.Admin;
import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.service.IAdminService;
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
    @PostMapping("/login")
    public Admin login(@RequestParam String adminId,@RequestParam String password) {
        return iAdminService.login(adminId, password);
    }
    @PostMapping("/passwordForget")     //忘记密码和修改密码共用
    int passwordForget(@RequestParam String adminId, @RequestParam String newPassword,@RequestParam String emailCode){
        return iAdminService.passwordForget(adminId,newPassword,emailCode);
    }
    @PostMapping("/sendForgetPasswordEmailCode")
    int sendForgetPasswordEmailCode(@RequestParam String adminId){
        return iAdminService.sendForgetPasswordEmailCode(adminId);
    }
    @GetMapping("/getAllMedicalRecord")//查看所有病历
    public List<MedicalRecord> getAllMedicalRecord (){
        return iAdminService.getAllMedicalRecord ();
    }
    @GetMapping("/searchMedicalRecords")//搜索病历
    public List<MedicalRecord> searchMedicalRecords (@RequestParam(value = "doctorId",required = false) String doctorId,
                                                     @RequestParam(value = "userId",required = false) String userId,
                                                     @RequestParam(value = "date",required = false) LocalDateTime date){
        return iAdminService.searchMedicalRecords (doctorId, userId, date);
    }
    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors (){
        return iAdminService.getAllDoctors();
    }
}
