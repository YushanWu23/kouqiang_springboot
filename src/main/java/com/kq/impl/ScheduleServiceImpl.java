package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.dao.IScheduleDao;
import com.kq.pojo.Doctor;
import com.kq.pojo.Schedule;
import com.kq.service.IDoctorService;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleServiceImpl implements IScheduleService {
    @Resource
    IScheduleDao iScheduleDao;
    @Resource
    IDoctorDao iDoctorDao;
   @Override
    public Schedule createSchedule(String doctorId, LocalDateTime startTime, LocalDateTime endTime, int maxReservations){
       checkScheduleConflict(doctorId, startTime, endTime);
       Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
       if (doctor == null) {
           throw new IllegalArgumentException("医生ID不存在: " + doctorId);
       }
       Schedule schedule = new Schedule();
       schedule.setDoctor(doctor);
       schedule.setStartTime(startTime);
       schedule.setEndTime(endTime);
       schedule.setMaxReservations(maxReservations);
       schedule.setStatus("Active");
       iScheduleDao.save(schedule);
       return schedule;
    }
    private void checkScheduleConflict(String doctorId, LocalDateTime startTime, LocalDateTime endTime){
        List<Schedule> conflicts = iScheduleDao.findConflictingSchedules(doctorId,startTime,endTime,null);
        if(!conflicts.isEmpty()){
            throw new IllegalStateException("排班时间冲突，请选择其他时间段");
        }
    }

}
