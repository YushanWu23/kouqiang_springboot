package com.kq.dao;

import com.kq.pojo.MedicalRecord;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMedicalRecordDao extends JpaRepository<MedicalRecord,Integer> {
    @Transactional
    MedicalRecord findMedicalRecordByMedicalRecordId(int id);
    List<MedicalRecord> getMedicalRecordsByUserUserId(String userId);
    @Query("SELECT m FROM MedicalRecord m " +
            "WHERE (:doctorId IS NULL OR m.doctor.doctorId = :doctorId) " +
            "AND (:userId IS NULL OR m.user.userId = :userId) " +
            "AND (:date IS NULL OR FUNCTION('DATE', m.visitTime) = FUNCTION('DATE', :date))")
    List<MedicalRecord> findByDoctorIdAndUserIdAndVisitDate(
            @Param("doctorId") String doctorId,
            @Param("userId") String userId,
            @Param("date") LocalDateTime date
    );
}
