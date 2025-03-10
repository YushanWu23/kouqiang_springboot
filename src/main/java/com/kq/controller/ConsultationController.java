package com.kq.controller;

import com.kq.pojo.ChatMessage;
import com.kq.pojo.Consultation;
import com.kq.service.IConsultationService;
import jakarta.annotation.Resource;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping(value = "/consultation")
public class ConsultationController {
    @Resource
    private IConsultationService consultationService;
    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/start")
    public void startConsultation( Principal principal,
                                   @RequestParam String doctorId) {
        if (principal == null) {
            throw new AccessDeniedException("用户未登录");
        }

        String userId = principal.getName();
        Consultation consultation = consultationService.createConsultation(userId, doctorId);

        // 通知医生
        messagingTemplate.convertAndSendToUser(
                doctorId,
                "/queue/consultation/requests",
                consultation
        );
    }

    @MessageMapping("/{consultationId}/message")
    public void handleMessage(Principal  principal,
                              @DestinationVariable Integer consultationId,
                              @RequestBody ChatMessage message) {
        if (principal == null) {
            throw new AccessDeniedException("用户未登录");
        }

        String userId = principal.getName();
        Consultation consultation = consultationService.validateParticipant(consultationId, userId);

        ChatMessage savedMessage = consultationService.saveMessage(consultationId, userId, message);

        // 发送给另一方
        String recipient = principal.equals(consultation.getUser().getUserId()) ?
                consultation.getDoctor().getDoctorId() :
                consultation.getUser().getUserId();

        messagingTemplate.convertAndSendToUser(
                recipient,
                "/queue/consultation/messages",
                savedMessage
        );
    }
}
