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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

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
        System.out.println("查询医生信息，doctorId=" + doctorId);
        User user = iUserDao.findUserByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        Doctor doctor = iDoctorDao.findDoctorByDoctorId(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("医生不存在");
        }
        System.out.println("创建咨询记录：");
        System.out.println("用户 ID: " + user.getUserId());
        System.out.println("医生 ID: " + doctor.getDoctorId());

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
    @Override
    public List<ChatMessage> getMessages( Integer consultationId){
        return iChatMessageDao.getChatMessagesByConsultationConsultationId(consultationId);
    }
    @Override
    public Consultation acceptConsultation(Integer consultationId, String doctorId) {
        Consultation consultation = iConsultationDao.findByConsultationId(consultationId);
        if (consultation == null) {
            throw new IllegalArgumentException("咨询不存在");
        }

        // 验证医生身份
        if (!consultation.getDoctor().getDoctorId().equals(doctorId)) {
            throw new IllegalArgumentException("无权接受该咨询");
        }

        consultation.setStatus("进行中");
        consultation.setDoctorOnline(true);
        return iConsultationDao.save(consultation);
    }
    @Override
    public List<Consultation> getConsultationRequests(String userId){
        return iConsultationDao.findByUserUserIdAndStatus(userId, "请求中");
    }
    @Override
    public Consultation endConsultation(Integer consultationId) {
        Consultation consultation = iConsultationDao.findByConsultationId(consultationId);
        consultation.setStatus("已结束");
        consultation.setEndTime(LocalDateTime.now());
        consultation.setUserOnline(false);
        consultation.setDoctorOnline(false);
        return iConsultationDao.save(consultation);
    }
}
