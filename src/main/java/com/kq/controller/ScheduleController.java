package com.kq.controller;

import com.kq.pojo.Schedule;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    IScheduleService iScheduleService;
    @GetMapping("/getScheduleByDoctorId")
    public List<Schedule> getScheduleByDoctorId(@RequestParam String doctorId,
                                                @RequestParam String startDate,
                                                @RequestParam String endDate) {
        return iScheduleService.getScheduleByDoctorId(doctorId, startDate, endDate);
    }
    @PostMapping("/createSchedule")
    public ResponseEntity<String>  createSchedule(@RequestParam String doctorId,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime,
                                   @RequestParam int maxReservations) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.of("Asia/Shanghai")); // 指定时区
            LocalDateTime startTimeParsed = LocalDateTime.parse(startTime, formatter);
            LocalDateTime endTimeParsed = LocalDateTime.parse(endTime, formatter);
            Schedule schedule = iScheduleService.createSchedule(doctorId, startTimeParsed, endTimeParsed, maxReservations);
            return ResponseEntity.ok("排班创建成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/updateSchedule")
    public ResponseEntity<String> updateSchedule(@RequestParam int scheduleId,
                                                 @RequestParam String doctorId,
                                                 @RequestParam String startTime,
                                                 @RequestParam String endTime,
                                                 @RequestParam int maxReservations) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.of("Asia/Shanghai")); // 指定时区
            LocalDateTime startTimeParsed = LocalDateTime.parse(startTime, formatter);
            LocalDateTime endTimeParsed = LocalDateTime.parse(endTime, formatter);
            iScheduleService.updateSchedule(scheduleId, doctorId, startTimeParsed, endTimeParsed, maxReservations);
            return ResponseEntity.ok("排班更新成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/deleteSchedule")
    public ResponseEntity<String> deleteSchedule(@RequestParam int scheduleId) {
        try {
            iScheduleService.deleteSchedule(scheduleId);
            return ResponseEntity.ok("排班删除成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getAllSchedules")
    public List<Schedule> getAllSchedules(){
        return iScheduleService.getAllSchedules();
    }
}
