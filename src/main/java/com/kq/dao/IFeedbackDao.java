package com.kq.dao;

import com.kq.pojo.DeliveryAddress;
import com.kq.pojo.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackDao extends JpaRepository<Feedback,Integer> {

    List<Feedback> findFeedbacksByUserUserId(String userId);
    Feedback findFeedbackByFeedbackId(int feedbackId);
}
