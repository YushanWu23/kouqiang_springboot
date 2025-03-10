package com.kq.service;

import com.kq.pojo.ChatMessage;
import com.kq.pojo.Consultation;
import org.springframework.stereotype.Service;

@Service
public interface IConsultationService {
    Consultation createConsultation(String userId, String doctorId);
    ChatMessage saveMessage(Integer consultationId, String senderId, ChatMessage message);
    void updateOnlineStatus(Integer consultationId, String userId, boolean online);
    Consultation validateParticipant(Integer consultationId, String userId);
}
