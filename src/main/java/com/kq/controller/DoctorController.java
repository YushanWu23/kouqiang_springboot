package com.kq.controller;

import com.kq.pojo.Doctor;
import com.kq.pojo.MedicalRecord;
import com.kq.pojo.Reservation;
import com.kq.pojo.Schedule;
import com.kq.service.IDoctorService;
import com.kq.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    IDoctorService iDoctorService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/getDoctorInfo")
    public Doctor getByDoctorId(@RequestParam String doctorId){
        return iDoctorService.getByDoctorId(doctorId);
    }
    @GetMapping("/getByDoctorNameContaining")
    public List<Doctor> getByDoctorNameContaining(@RequestParam String doctorName){
        return iDoctorService.getByDoctorNameContaining(doctorName);
    }
    @GetMapping("/getAll")
    List<Doctor> getAll(){
        return iDoctorService.getAll();
    }
    @GetMapping("/getBySpecialty")
    List<Doctor> getBySpecialty(@RequestParam String specialty){//根据领域找医生
        return iDoctorService.getBySpecialty(specialty);
    }
    @PostMapping("/login")
    String login(@RequestParam String doctorId, @RequestParam String pwd){
        return iDoctorService.login(doctorId,pwd)!=null? jwtTokenUtil.generateToken(doctorId) :null;
    }
    @PostMapping("/passwordForget")     //忘记密码和修改密码共用
    int passwordForget(@RequestParam String doctorId, @RequestParam String newPassword,@RequestParam String emailCode){
        return iDoctorService.passwordForget(doctorId,newPassword,emailCode);
    }
    @PostMapping("/sendForgetPasswordEmailCode")
    int sendForgetPasswordEmailCode(@RequestParam String doctorId){
        return iDoctorService.sendForgetPasswordEmailCode(doctorId);
    }
    @PostMapping("/updateDoctorInfo")
    int updateUserInfo (@RequestParam String doctorId,@RequestParam String doctorName,@RequestParam int doctorSex,@RequestParam String doctorImg,@RequestParam String specialty,@RequestParam String title){
        return iDoctorService.updateDoctorInfo (doctorId,doctorName,doctorSex,doctorImg,specialty,title);
    }
    @PostMapping("/updateOnlineStatus")
    int updateOnlineStatus (@RequestParam String doctorId,@RequestParam boolean onlineStatus){
        return iDoctorService.updateOnlineStatus (doctorId,onlineStatus);
    }
    @GetMapping("/getScheduleByDoctorId")//得到排班信息
    List<Schedule> getScheduleByDoctorId (@RequestParam String doctorId,@RequestParam String startDate,@RequestParam String endDate){
        return iDoctorService.getScheduleByDoctorId (doctorId,startDate,endDate);
    }

}
