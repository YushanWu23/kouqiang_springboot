package com.kq.dao;

import com.kq.pojo.unuse.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IDoctorDao extends JpaRepository<Doctor,Integer> {
    List<Doctor> findDoctorsByDoctorNameContaining(String name);
    List<Doctor> findDoctorsByOrderTypeId(int orderTypeId);

    @Transactional
    Doctor findDoctorByDoctorId(String id);
    Doctor findDoctorByDoctorIdAndPassword(String doctorId,String password);
    long countByDoctorId(String doctorId);
}
