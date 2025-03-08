package com.kq.dao;

import com.kq.pojo.MedicalRecord;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IMedicalRecordDao extends JpaRepository<MedicalRecord,Integer> {
    @Transactional
    MedicalRecord findMedicalRecordByMedicalRecordId(int id);
}
