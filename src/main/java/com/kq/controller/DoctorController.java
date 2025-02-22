package com.kq.controller;

import com.kq.pojo.unuse.Doctor;
import com.kq.service.IDoctorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    IDoctorService iDoctorService;
    @GetMapping("/getDoctorByDoctorId")
    public Doctor getByDoctorId(@RequestParam String doctorId){
        return iDoctorService.getByDoctorId(doctorId);
    }
    @GetMapping("/getByDoctorNameContaining/{doctorName}")
    public List<Doctor> getByDoctorName(@RequestParam String doctorName){
        return iDoctorService.getByDoctorNameContaining(doctorName);
    }
    @GetMapping("/getAll")
    List<Doctor> getAll(){
        return iDoctorService.getAll();
    }
    @GetMapping("/getByOrderTypeId")
    List<Doctor> getByOrderTypeId(@RequestParam int orderTypeId){
        return iDoctorService.getByOrderTypeId(orderTypeId);
    }

}
