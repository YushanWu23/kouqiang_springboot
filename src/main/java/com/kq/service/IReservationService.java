package com.kq.service;

import com.kq.pojo.Reservation;
import com.kq.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface IReservationService {
    Reservation createReservation(int scheduleId, String userId,String doctorId);
    Reservation updateReservationStatus(int reservationId, int status);
    int deleteReservation(int reservationId);
    List<Reservation> getReservationByUserId( String userId);
    Reservation getReservationByUserIdAndScheduleId( String userId,int scheduleId);
    List<User> getUserByDoctorIdAndReservationStatus( String doctorId,  int reservationStatus);
    List<Reservation> getTodayReservation(String doctorId);
}
