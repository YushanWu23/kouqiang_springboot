package com.kq.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("18158377221@163.com");  // 确保与授权用户一致
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败");
        }
    }
}
