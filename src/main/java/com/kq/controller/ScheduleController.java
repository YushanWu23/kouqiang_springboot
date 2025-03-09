package com.kq.controller;

import com.kq.pojo.Schedule;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    IScheduleService iScheduleService;
    @PostMapping
    public Schedule createSchedule(@RequestBody String doctorId,@RequestBody LocalDateTime startTime,@RequestBody LocalDateTime endTime,@RequestBody int maxReservations) {
        return iScheduleService.createSchedule(doctorId, startTime, endTime, maxReservations);
    }
}
