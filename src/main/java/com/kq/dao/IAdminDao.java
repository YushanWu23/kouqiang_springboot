package com.kq.dao;

import com.kq.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDao extends JpaRepository<Admin,String> {
    Admin findAdminByAdminIdAndPassword(String adminId, String password);
}
