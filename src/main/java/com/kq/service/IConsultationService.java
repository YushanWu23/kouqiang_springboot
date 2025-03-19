package com.kq.service;

import com.kq.pojo.ChatMessage;
import com.kq.pojo.Consultation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Service
public interface IConsultationService {
    Consultation createConsultation(String userId, String doctorId);
    ChatMessage saveMessage(Integer consultationId, String senderId, ChatMessage message);
    void updateOnlineStatus(Integer consultationId, String userId, boolean online);
    Consultation validateParticipant(Integer consultationId, String userId);
    List<ChatMessage> getMessages( Integer consultationId);
    Consultation acceptConsultation( Integer consultationId,String doctorId);
    List<Consultation> getConsultationRequests(String userId);
    Consultation endConsultation(Integer consultationId);
}
