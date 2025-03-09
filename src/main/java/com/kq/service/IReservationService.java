package com.kq.service;

import com.kq.pojo.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface IReservationService {
    Reservation createReservation(int scheduleId, String userId,String doctorId);
    Reservation updateReservationStatus(int reservationId, String status);
    int deleteReservation(int reservationId);
}
