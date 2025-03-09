package com.kq.service;

import com.kq.pojo.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface IScheduleService {
    Schedule createSchedule(String doctorId, LocalDateTime startTime, LocalDateTime endTime, int maxReservations);
    Schedule updateSchedule(int scheduleId,String doctorId, LocalDateTime startTime, LocalDateTime endTime, int maxReservations);
    int deleteSchedule(int scheduleId);
}
