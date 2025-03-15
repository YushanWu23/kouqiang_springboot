package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.dao.IReservationDao;
import com.kq.dao.IScheduleDao;
import com.kq.pojo.Doctor;
import com.kq.pojo.Reservation;
import com.kq.pojo.Schedule;
import com.kq.service.IDoctorService;
import com.kq.service.IScheduleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
public class ScheduleServiceImpl implements IScheduleService {
    @Resource
    IScheduleDao iScheduleDao;
    @Resource
    IDoctorDao iDoctorDao;
    @Resource
    IReservationDao iReservationDao;
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
       schedule.setStatus(0);
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
        schedule.setStatus(0);
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
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = iScheduleDao.findAllOrderByScheduleIdDesc();
        LocalDateTime now = LocalDateTime.now();
        for (Schedule schedule : schedules) {
            if (schedule.getEndTime().isBefore(now)){
                schedule.setStatus(2);
                iScheduleDao.save(schedule);
            }
            // 检查已预约的排班是否超过结束时间
            List<Reservation> reservations = iReservationDao.findBySchedule(schedule);
            for (Reservation reservation : reservations) {
                if (reservation.getStatus()==1 && schedule.getEndTime().isBefore(now)) {
                    reservation.setStatus(2);
                    iReservationDao.save(reservation);
                }
                System.out.println(reservation.getStatus());
            }
        }
        return schedules;
    }
    @Override
    public List<Schedule> getScheduleByDoctorIdAndTime(String doctorId, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", DateTimeFormatter.ISO_DATE_TIME);
        List<Schedule> schedules = iScheduleDao.findSchedulesByDoctorIdAndDateRange(doctorId, start, end);

        LocalDateTime now = LocalDateTime.now();
        for (Schedule schedule : schedules) {
            if (schedule.getEndTime().isBefore(now) ){
                schedule.setStatus(2); // 更新状态为 "finished"
                iScheduleDao.save(schedule);
            }
        }
        return schedules;
    }
    @Override
    public List<Schedule> getScheduleByDoctorId(String doctorId) {
        List<Schedule> schedules = iScheduleDao.findByDoctorDoctorId(doctorId);
        LocalDateTime now = LocalDateTime.now();
        for (Schedule schedule : schedules) {
            if (schedule.getEndTime().isBefore(now)) {
                schedule.setStatus(2); // 更新状态为 "finished"
                iScheduleDao.save(schedule);
            }
        }
        return schedules;
    }
}
