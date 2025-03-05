package com.kq.service;

import com.kq.pojo.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IFeedbackService {
    List<Feedback> getFeedbackAll();
    List<Feedback> getFeedbackByUserId( String userId);
    int saveFeedback(String feedbackExplain, MultipartFile[] files,String userId);
    int updateFeedback ( int feedbackId, String feedbackExplain);
    int removeFeedback( int feedbackId, String userId);
}
