package com.kq.controller;

import com.kq.pojo.Schedule;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
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
    @PostMapping("/createSchedule")
    public Schedule createSchedule(@RequestParam String doctorId,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime,
                                   @RequestParam int maxReservations) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai")); // 指定时区
        LocalDateTime startTimeParsed = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endTimeParsed = LocalDateTime.parse(endTime, formatter);
        return iScheduleService.createSchedule(doctorId, startTimeParsed, endTimeParsed, maxReservations);
    }

    @PostMapping("/updateSchedule")
    public Schedule updateSchedule(@RequestParam int scheduleId,
                                   @RequestParam String doctorId,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime,
                                   @RequestParam int maxReservations) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai")); // 指定时区
        LocalDateTime startTimeParsed = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endTimeParsed = LocalDateTime.parse(endTime, formatter);
        return iScheduleService.updateSchedule(scheduleId, doctorId, startTimeParsed, endTimeParsed, maxReservations);
    }
    @PostMapping("/deleteSchedule")
    public int deleteSchedule(@RequestParam int scheduleId) {
        return iScheduleService.deleteSchedule(scheduleId);
    }
    @GetMapping("/getAllSchedules")
    public List<Schedule> getAllSchedules(){
        return iScheduleService.getAllSchedules();
    }
}
