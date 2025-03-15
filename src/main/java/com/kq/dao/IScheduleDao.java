package com.kq.dao;

import com.kq.pojo.Schedule;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IScheduleDao extends JpaRepository<Schedule,Integer> {
    @Transactional
    Schedule findScheduleByScheduleId(int id);
    List<Schedule> findByDoctorDoctorIdAndStartTimeBetweenOrderByStartTimeAsc(String doctorId, LocalDateTime start, LocalDateTime end);
    List<Schedule> findByDoctorDoctorId(String doctorId);
    @Query("SELECT s FROM Schedule s WHERE " +
            "s.doctor.doctorId = :doctorId AND " +
            "(s.startTime < :endTime AND s.endTime > :startTime) AND " +
            "(s.scheduleId != :excludeId OR :excludeId IS NULL)")
    List<Schedule> findConflictingSchedules(@Param("doctorId") String doctorId,
                                            @Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime,
                                            @Param("excludeId") Integer excludeId);
    @Query("SELECT s FROM Schedule s WHERE s.doctor.doctorId = :doctorId AND s.startTime >= :start AND s.endTime <= :end ORDER BY s.scheduleId DESC")
    List<Schedule> findSchedulesByDoctorIdAndDateRange(@Param("doctorId") String doctorId,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);
    @Query("SELECT s FROM Schedule s ORDER BY s.scheduleId DESC")
    List<Schedule> findAllOrderByScheduleIdDesc();
}

