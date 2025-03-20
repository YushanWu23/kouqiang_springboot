package com.kq.impl;

import com.kq.dao.IDoctorDao;
import com.kq.dao.IReservationDao;
import com.kq.dao.IScheduleDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.*;
import com.kq.service.IReservationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        reservation.setStatus(1);//已预约
        reservation.setBookTime(LocalDateTime.now());
        reservation.setUser(user);
        reservation.setDoctor(doctor);
        schedule.setCurrentReservations(schedule.getCurrentReservations()+1);//预约数加一
        if (Objects.equals(schedule.getCurrentReservations(), schedule.getMaxReservations())) {
            schedule.setStatus(1); // 设置排班状态为 1（已满）
        }
        iScheduleDao.save(schedule);
        reservation.setSchedule(schedule);
        iReservationDao.save(reservation);
        return reservation;
    }
    @Override
    public Reservation updateReservationStatus(int reservationId, int status){
        Reservation reservation = iReservationDao.findReservationByReservationId(reservationId);
        reservation.setStatus(status);
        iReservationDao.save(reservation);
        return reservation;
    }
    @Override
    public int deleteReservation(int reservationId){
        Reservation reservation = iReservationDao.findReservationByReservationId(reservationId);
        reservation.setStatus(0);//未预约
        iReservationDao.save(reservation);
        Schedule schedule = reservation.getSchedule();
        schedule.setCurrentReservations(schedule.getCurrentReservations()-1);
        iScheduleDao.save(schedule);
        return 1;
    }
    @Override
    public List<Reservation> getReservationByUserId( String userId) {
        List<Reservation> reservations = iReservationDao.getReservationsByUserUserId(userId);
        /*for (Reservation reservation : reservations) {
            System.out.println(reservation.getStatus());
        }*/
        return reservations;
    }
    @Override
    public Reservation getReservationByUserIdAndScheduleId( String userId,int scheduleId) {
        System.out.println(userId);
        System.out.println(scheduleId);
        return iReservationDao.getReservationByUserUserIdAndScheduleScheduleId(userId,scheduleId);

    }
    @Override
    public List<User> getUserByDoctorIdAndReservationStatus( String doctorId,  int reservationStatus){
        List<Reservation> reservationList = iReservationDao.getReservationsByDoctorDoctorIdAndStatus(doctorId,reservationStatus);
        List<User> userList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            userList.add(reservation.getUser());
        }
        return userList;
    }
    @Override
    public List<Reservation> getTodayReservation(String doctorId) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 查询关联当前时间的排班预约
        return iReservationDao.findByDoctorDoctorIdAndSchedule(doctorId, now);
    }
}
