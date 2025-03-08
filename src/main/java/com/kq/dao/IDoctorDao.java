package com.kq.dao;

import com.kq.pojo.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDoctorDao extends JpaRepository<Doctor,Integer> {
    List<Doctor> findDoctorsByDoctorNameContaining(String name);
    List<Doctor> findDoctorsBySpecialty(String specialty);

    @Transactional
    Doctor findDoctorByDoctorId(String id);
    Doctor findDoctorByDoctorIdAndPassword(String doctorId,String password);
    long countByDoctorId(String doctorId);
}
