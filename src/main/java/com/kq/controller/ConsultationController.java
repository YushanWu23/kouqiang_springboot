package com.kq.controller;

import com.kq.pojo.ChatMessage;
import com.kq.pojo.Consultation;
import com.kq.service.IConsultationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/consultation")
public class ConsultationController {
    @Resource
    private IConsultationService iConsultationService;
    @Resource
    private SimpMessagingTemplate messagingTemplate;
    @GetMapping("/requests")
    public List<Consultation> getConsultationRequests(Principal principal) {
        if (principal == null) {
            throw new AccessDeniedException("用户未登录");
        }
        String userId = principal.getName();
        return iConsultationService.getConsultationRequests(userId);
    }
    @PostMapping("/start")
    public ResponseEntity<?> startConsultationHttp(
            @RequestBody Map<String, String> request,
            Principal principal
    ) {
        String doctorId = request.get("doctorId");
        String userId = principal.getName();

        Consultation consultation = iConsultationService.createConsultation(userId, doctorId);

        // WebSocket通知医生
        messagingTemplate.convertAndSendToUser(
                doctorId,
                "/queue/consultation/requests",
                consultation
        );

        return ResponseEntity.ok(consultation);
    }
    /*@MessageMapping("/start")
    public void startConsultation( Principal principal
            , @Payload Map<String, String> payload) {
        String doctorId = payload.get("doctorId");
        System.out.println("===== 进入 startConsultation 方法 =====");
        System.out.println("Principal 对象: " + principal);
        if (principal == null) {
            System.out.println("Principal 为 null，用户未登录");
            throw new AccessDeniedException("用户未登录");
        }

        String userId = principal.getName();
        System.out.println("当前用户 ID: " + userId);
        System.out.println("传递的 doctorId: " + doctorId);
        Consultation consultation = iConsultationService.createConsultation(userId, doctorId);

        // 通知医生
        messagingTemplate.convertAndSendToUser(
                doctorId,
                "/queue/consultation/requests",
                consultation
        );
    }*/
    @MessageMapping("/start")
    public void startConsultation(Principal principal, @Payload Map<String, String> payload) {
        String doctorId = payload.get("doctorId");
        String userId = principal.getName();

        Consultation consultation = iConsultationService.createConsultation(userId, doctorId);

        // 通知医生
        messagingTemplate.convertAndSendToUser(
                doctorId,
                "/queue/consultation/requests",
                consultation
        );
    }
    @MessageMapping("/{consultationId}/end")
    public void endConsultation(
            Principal principal,
            @DestinationVariable Integer consultationId) {

        String userId = principal.getName();
        Consultation consultation = iConsultationService.validateParticipant(consultationId, userId);

        // 更新状态
        Consultation endedConsultation = iConsultationService.endConsultation(consultationId);

        // 通知双方
        messagingTemplate.convertAndSendToUser(
                consultation.getUser().getUserId(),
                "/queue/consultation/status",
                endedConsultation
        );

        messagingTemplate.convertAndSendToUser(
                consultation.getDoctor().getDoctorId(),
                "/queue/consultation/status",
                endedConsultation
        );
    }
    @MessageMapping("/{consultationId}/message")
    public void handleMessage(
            Principal principal,
            @DestinationVariable Integer consultationId,
            @Payload ChatMessage message
    ) {
        String userId = principal.getName();
        Consultation consultation = iConsultationService.validateParticipant(consultationId, userId);

        // 保存消息
        ChatMessage savedMessage = iConsultationService.saveMessage(consultationId, userId, message);
        System.out.println("消息已保存到数据库");
        // 发送给自己
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/consultation/messages",
                savedMessage
        );
        // 发送给另一方
        String recipient = consultation.getUser().getUserId().equals(userId) ?
                consultation.getDoctor().getDoctorId() :
                consultation.getUser().getUserId();
        System.out.println("准备发送消息给接收者: " + recipient);
        messagingTemplate.convertAndSendToUser(
                recipient,
                "/queue/consultation/messages",
                savedMessage
        );
        System.out.println("消息已发送给接收者");
    }
    @GetMapping("/messages/{consultationId}")// 获取历史消息
    public List<ChatMessage> getMessages(@PathVariable Integer consultationId) {
        return iConsultationService.getMessages(consultationId);
    }
    @MessageMapping("/accept")
    public void acceptConsultation(
            @Payload Map<String, Integer> payload,
            Principal principal
    ) {
        Integer consultationId = payload.get("consultationId");
        String doctorId = principal.getName();

        Consultation consultation = iConsultationService.acceptConsultation(consultationId, doctorId);
        System.out.println("已接收");
        // 通知双方
        messagingTemplate.convertAndSendToUser(
                consultation.getUser().getUserId(),
                "/queue/consultation/status",
                consultation
        );
        messagingTemplate.convertAndSendToUser(
                doctorId,
                "/queue/consultation/status",
                consultation
        );
    }
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }

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

            // 返回文件访问路径
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
}
