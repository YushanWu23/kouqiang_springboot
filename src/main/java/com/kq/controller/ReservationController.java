package com.kq.controller;

import com.kq.pojo.Reservation;
import com.kq.service.IReservationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class ReservationController {
    @Resource
    IReservationService iReservationService;
    @PostMapping("/createReservation")
    public Reservation createReservation(@RequestParam int scheduleId,@RequestParam String userId,@RequestParam String doctorId){
        return iReservationService.createReservation(scheduleId, userId, doctorId);
    }
    @PostMapping("/updateReservationStatus")
    public Reservation updateReservationStatus(@RequestParam int reservationId,@RequestParam String status){
        return iReservationService.updateReservationStatus(reservationId, status);
    }
    @PostMapping("/deleteReservation")
    public int deleteReservation(@RequestParam int reservationId){
        return iReservationService.deleteReservation(reservationId);
    }
}
