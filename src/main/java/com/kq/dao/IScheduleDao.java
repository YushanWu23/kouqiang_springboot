package com.kq.dao;

import com.kq.pojo.Schedule;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
