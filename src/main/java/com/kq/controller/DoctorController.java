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
    @GetMapping("/getDoctorByDoctorId")
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
    @PostMapping("/updateDoctorInfo")//更改信息
    int updateUserInfo (@RequestParam String doctorId,@RequestParam String doctorName,@RequestParam int doctorSex,@RequestParam String doctorImg,@RequestParam String specialty,@RequestParam String title){
        return iDoctorService.updateDoctorInfo (doctorId,doctorName,doctorSex,doctorImg,specialty,title);
    }
    @PostMapping("/updateOnlineStatus")//更改在线状态
    int updateOnlineStatus (@RequestParam String doctorId,@RequestParam boolean onlineStatus){
        return iDoctorService.updateOnlineStatus (doctorId,onlineStatus);
    }
    @GetMapping("/getScheduleByDoctorId")//得到排班信息
    List<Schedule> getScheduleByDoctorId (@RequestParam String doctorId,@RequestParam String startDate,@RequestParam String endDate){
        return iDoctorService.getScheduleByDoctorId (doctorId,startDate,endDate);
    }
    @GetMapping("/getTodayReservation")//得到今日预约信息
    List<Reservation> getTodayReservation (@RequestParam String doctorId){
        return iDoctorService.getTodayReservation (doctorId);
    }
    @PostMapping("/createMedicalRecord")//创建病历
    MedicalRecord createMedicalRecord (@RequestParam String doctorId, @RequestParam String userId,@RequestParam String diagnosis,@RequestParam String treatmentPlan,@RequestParam(value = "files",required = false) MultipartFile[] files){
        return iDoctorService.createMedicalRecord (doctorId,userId,diagnosis,treatmentPlan,files);
    }
}
