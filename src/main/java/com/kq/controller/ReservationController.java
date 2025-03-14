package com.kq.controller;

import com.kq.pojo.Reservation;
import com.kq.pojo.Schedule;
import com.kq.service.IReservationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Reservation updateReservationStatus(@RequestParam int reservationId,@RequestParam int status){
        return iReservationService.updateReservationStatus(reservationId, status);
    }
    @PostMapping("/deleteReservation")
    public int deleteReservation(@RequestParam int reservationId){
        return iReservationService.deleteReservation(reservationId);
    }
    @GetMapping("/getReservationByUserId")
    public List<Reservation> getReservationByUserId(@RequestParam String userId) {
        return iReservationService.getReservationByUserId(userId);
    }
    @GetMapping("/getReservationByUserIdAndScheduleId")
    public Reservation getReservationByUserIdAndScheduleId( String userId,int scheduleId){
        return iReservationService.getReservationByUserIdAndScheduleId(userId,scheduleId);
    }
}
