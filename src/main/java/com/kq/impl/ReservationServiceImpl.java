package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.dao.IReservationDao;
import com.kq.dao.IScheduleDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.*;
import com.kq.service.IReservationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationServiceImpl implements IReservationService {
    @Resource
    IReservationDao iReservationDao;
    @Resource
    IDoctorDao iDoctorDao;
    @Resource
    IScheduleDao iScheduleDao;
    @Resource
    IUserDao iUserDao;
    @Override
    public Reservation createReservation(int scheduleId, String userId, String doctorId){
        User user = iUserDao.findUserByUserId(userId);
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        Schedule schedule = iScheduleDao.findScheduleByScheduleId(scheduleId);
        if (schedule.getCurrentReservations()>=schedule.getMaxReservations()){
            throw new IllegalStateException("该排班已约满");
        }
        Reservation reservation = new Reservation();
        reservation.setStatus("待确认");
        reservation.setBookTime(LocalDateTime.now());
        reservation.setUser(user);
        reservation.setDoctor(doctor);
        reservation.setSchedule(schedule);
        schedule.setCurrentReservations(schedule.getCurrentReservations()+1);//预约数加一
        iScheduleDao.save(schedule);
        iReservationDao.save(reservation);
        return reservation;
    }
    @Override
    public Reservation updateReservationStatus(int reservationId, String status){
        Reservation reservation = iReservationDao.findReservationByReservationId(reservationId);
        reservation.setStatus(status);
        iReservationDao.save(reservation);
        return reservation;
    }
    @Override
    public int deleteReservation(int reservationId){
        Reservation reservation = iReservationDao.findReservationByReservationId(reservationId);
        reservation.setStatus("取消");
        iReservationDao.save(reservation);
        Schedule schedule = reservation.getSchedule();
        schedule.setCurrentReservations(schedule.getCurrentReservations()-1);
        iScheduleDao.save(schedule);
        return 1;
    }

}
