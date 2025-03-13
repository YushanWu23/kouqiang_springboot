package com.kq.controller;

import com.kq.pojo.Schedule;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    IScheduleService iScheduleService;
    @PostMapping("/createSchedule")
    public Schedule createSchedule(@RequestParam String doctorId,@RequestParam LocalDateTime startTime,@RequestParam LocalDateTime endTime,@RequestParam int maxReservations) {
        return iScheduleService.createSchedule(doctorId, startTime, endTime, maxReservations);
    }
    @PostMapping("/updateSchedule")
    public Schedule updateSchedule(@RequestParam int scheduleId,@RequestParam String doctorId,@RequestParam LocalDateTime startTime,@RequestParam LocalDateTime endTime,@RequestParam int maxReservations) {
        return iScheduleService.updateSchedule(scheduleId, doctorId,startTime, endTime, maxReservations);
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
