package com.kq.impl;

import com.kq.dao.IChatMessageDao;
import com.kq.dao.IConsultationDao;
import com.kq.dao.IDoctorDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.ChatMessage;
import com.kq.pojo.Consultation;
import com.kq.pojo.Doctor;
import com.kq.pojo.User;
import com.kq.service.IConsultationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@Component
public class ConsultationServiceImpl implements IConsultationService {
    @Resource
    private IConsultationDao iConsultationDao;
    @Resource
    private IDoctorDao iDoctorDao;
    @Resource
    private IUserDao iUserDao;
    @Resource
    private IChatMessageDao iChatMessageDao;
    @Override
    public Consultation createConsultation(String userId, String doctorId) {
        User user = iUserDao.findUserByUserId(userId);
        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        Consultation consultation = new Consultation();
        consultation.setUser(user);
        consultation.setDoctor(doctor);
        consultation.setStartTime(LocalDateTime.now());
        consultation.setStatus("请求中");
        consultation.setUserOnline(true);
        consultation.setDoctorOnline(false);
        iConsultationDao.save(consultation);
        return consultation;
    }
    @Override
    public ChatMessage saveMessage(Integer consultationId, String senderId, ChatMessage message) {
        Consultation consultation = iConsultationDao.findByConsultationId(consultationId);

        // 验证发送者身份
        if (!senderId.equals(consultation.getUser().getUserId()) &&
                !senderId.equals(consultation.getDoctor().getDoctorId())) {
            throw new IllegalStateException("无权发送消息");
        }

        message.setConsultation(consultation);
        message.setSendTime(LocalDateTime.now());
        message.setSender(senderId.equals(consultation.getDoctor().getDoctorId()) ?
                "医生" : "用户");

        return iChatMessageDao.save(message);
    }
    @Override
    public void updateOnlineStatus(Integer consultationId, String userId, boolean online) {
        Consultation consultation = iConsultationDao.findByConsultationId(consultationId);
        if (userId.equals(consultation.getUser().getUserId())) {
            consultation.setUserOnline(online);
        } else if (userId.equals(consultation.getDoctor().getDoctorId())) {
            consultation.setDoctorOnline(online);
        }
        iConsultationDao.save(consultation);
    }
    @Override
    public Consultation validateParticipant(Integer consultationId, String userId) {
        Consultation consultation = iConsultationDao.findByConsultationId(consultationId);
        if (!userId.equals(consultation.getUser().getUserId()) &&
                !userId.equals(consultation.getDoctor().getDoctorId())) {
            throw new IllegalStateException("无权访问该咨询");
        }
        return consultation;
    }
}
