package com.kq.impl;

import com.kq.dao.IFeedbackDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.Feedback;
import com.kq.pojo.User;
import com.kq.service.IFeedbackService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackServiceImpl implements IFeedbackService {
    @Resource
    IFeedbackDao iFeedbackDao;
    @Resource
    IUserDao iUserDao;
    @Override
    public List<Feedback> getFeedbackByUserId(String userId){
        return iFeedbackDao.findFeedbacksByUserUserId(userId);
    }
    @Override
    public int saveFeedback( String feedbackExplain,String userId){
        Feedback feedback = new Feedback();
        feedback.setFeedbackExplain(feedbackExplain);
        User user = iUserDao.findUserByUserId(userId);
        feedback.setUser(user);
        iFeedbackDao.save(feedback);
        return 1;
    }
    @Override
    public int updateFeedback ( int feedbackId, String feedbackExplain){
        Feedback feedback = iFeedbackDao.findFeedbackByFeedbackId(feedbackId);
        feedback.setFeedbackExplain(feedbackExplain);
        iFeedbackDao.save(feedback);
        return 1;
    }
    @Override
    public int removeFeedback( int feedbackId, String userId){
        Feedback feedback = iFeedbackDao.findFeedbackByFeedbackId(feedbackId);
        User user = iUserDao.findUserByUserId(userId);
        user.removeFeedback(feedback);
        iUserDao.save(user);
        return 1;
    }
}
