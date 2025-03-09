package com.kq.controller;

import com.kq.pojo.Reservation;
import com.kq.service.IReservationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class ReservationController {
    @Resource
    IReservationService iReservationService;
    @PostMapping("/createReservation")
    public Reservation createReservation(int scheduleId, String userId, String doctorId){
        return iReservationService.createReservation(scheduleId, userId, doctorId);
    }
    @PostMapping("/updateReservationStatus")
    public Reservation updateReservationStatus(int reservationId, String status){
        return iReservationService.updateReservationStatus(reservationId, status);
    }
    @PostMapping("/deleteReservation")
    public int deleteReservation(int reservationId){
        return iReservationService.deleteReservation(reservationId);
    }
}
