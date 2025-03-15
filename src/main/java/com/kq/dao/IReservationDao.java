package com.kq.dao;

import com.kq.pojo.Reservation;
import com.kq.pojo.Schedule;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IReservationDao extends JpaRepository<Reservation,Integer> {
    @Transactional
    Reservation findReservationByReservationId(int id);
    List<Reservation> findByDoctorDoctorIdAndBookTimeBetween(String doctorId, LocalDateTime start, LocalDateTime end);
    List<Reservation> findBySchedule(Schedule schedule);
    List<Reservation> getReservationsByUserUserId(String userId);
    Reservation getReservationByUserUserIdAndScheduleScheduleId(String userId, int scheduleId);
    List<Reservation> getReservationsByDoctorDoctorIdAndStatus(String doctorId, int reservationStatus);
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.doctor.doctorId = :doctorId " +
            "AND r.schedule.startTime <= :currentTime " +
            "AND r.schedule.endTime >= :currentTime " +
            "AND r.status = 1") // 1表示有效预约
    List<Reservation> findByDoctorDoctorIdAndSchedule(
            @Param("doctorId") String doctorId,
            @Param("currentTime") LocalDateTime currentTime
    );
}

