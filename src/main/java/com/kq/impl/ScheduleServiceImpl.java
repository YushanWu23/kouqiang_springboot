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
       checkScheduleConflict(doctorId, startTime, endTime,null);
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
    private void checkScheduleConflict(String doctorId, LocalDateTime startTime, LocalDateTime endTime, Integer excludeId){
        List<Schedule> conflicts = iScheduleDao.findConflictingSchedules(doctorId,startTime,endTime,excludeId);
        if(!conflicts.isEmpty()){
            throw new IllegalStateException("排班时间冲突，请选择其他时间段");
        }
    }
    @Override
    public Schedule updateSchedule(int scheduleId,String doctorId, LocalDateTime startTime, LocalDateTime endTime, int maxReservations){
       Schedule schedule = iScheduleDao.findScheduleByScheduleId(scheduleId);
       checkScheduleConflict(doctorId, startTime, endTime,scheduleId);
       Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("医生ID不存在: " + doctorId);
        }
        schedule.setDoctor(doctor);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setMaxReservations(maxReservations);
        schedule.setStatus("Active");
        iScheduleDao.save(schedule);
        return schedule;
    }
    @Override
    public int deleteSchedule(int scheduleId){
        Schedule schedule = iScheduleDao.findScheduleByScheduleId(scheduleId);
        if (schedule != null) {
            iScheduleDao.delete(schedule);
        } else {
            throw new IllegalArgumentException("排班ID不存在: " + scheduleId);
        }
        return 1;
    }
    @Override
    public List<Schedule> getAllSchedules(){
       return iScheduleDao.findAll();
    }
}
