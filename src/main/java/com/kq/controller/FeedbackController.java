package com.kq.controller;

import com.kq.pojo.Feedback;
import com.kq.service.IFeedbackService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/feedback")
public class FeedbackController {//反馈
    @Resource
    IFeedbackService iFeedbackService;
    @GetMapping("/getFeedbackAll")//根据用户编号查询反馈
    List<Feedback> getFeedbackAll(){
        return iFeedbackService.getFeedbackAll();
    }
    @GetMapping("/getFeedbackByUserId")//根据用户编号查询反馈
    List<Feedback> getFeedbackByUserId(@RequestParam String userId){
        return iFeedbackService.getFeedbackByUserId(userId);
    }
    @PostMapping("/saveFeedback")
    int saveFeedback(@RequestParam String feedbackExplain, @RequestParam("files") MultipartFile[] files, @RequestParam String userId){
        return iFeedbackService.saveFeedback(feedbackExplain, files, userId);
    }
    /*@PostMapping("/updateFeedback")
    int updateFeedback (@RequestParam int feedbackId,@RequestParam String feedbackExplain){
        return iFeedbackService.updateFeedback (feedbackId,feedbackExplain);
    }
    @GetMapping("/removeFeedback")
    int removeFeedback(@RequestParam int feedbackId,@RequestParam String userId){
        return iFeedbackService.removeFeedback(feedbackId,userId);
    }*/
}
