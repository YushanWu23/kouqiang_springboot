package com.kq.dao.forUser;

import com.kq.pojo.forUser.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackDao extends JpaRepository<Feedback,Integer> {

    List<Feedback> findFeedbacksByUserUserId(String userId);
    Feedback findFeedbackByFeedbackId(int feedbackId);
}
