package com.kq.impl.forUser;

import com.kq.dao.forUser.IFeedbackDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.forUser.Feedback;
import com.kq.pojo.User;
import com.kq.service.forUser.IFeedbackService;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FeedbackServiceImpl implements IFeedbackService {
    @Resource
    IFeedbackDao iFeedbackDao;
    @Resource
    IUserDao iUserDao;
    @Value("${upload.path}") // 从配置文件中读取图片存储路径
    private String uploadPath;

    @Override
    public List<Feedback> getFeedbackAll() {
        return iFeedbackDao.findAll();
    }
    @Override
    public List<Feedback> getFeedbackByUserId(String userId){
        return iFeedbackDao.findFeedbacksByUserUserId(userId);
    }
    @Override
    public int saveFeedback(String feedbackExplain, MultipartFile[] files,String userId){
        Feedback feedback = new Feedback();
        feedback.setFeedbackExplain(feedbackExplain);
        User user = iUserDao.findUserByUserId(userId);
        feedback.setUser(user);
        List<String> imageUrls = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 确保上传目录存在
                        Path uploadDir = Paths.get("D:\\kouqiang-uploadImg");
                        if (!Files.exists(uploadDir)) {
                            Files.createDirectories(uploadDir);
                        }

                        // 生成唯一的文件名
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadDir.resolve(fileName);

                        // 保存文件
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // 生成图片访问 URL
                        String imageUrl = "/uploads/" + fileName;
                        imageUrls.add(imageUrl);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store image file", e);
                    }
                }
            }
        }
        feedback.setImageUrls(imageUrls);
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
