package com.kq.dao;

import com.kq.pojo.Consultation;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultationDao extends JpaRepository<Consultation,Integer> {
    Consultation findByConsultationId(Integer consultationId);
}
