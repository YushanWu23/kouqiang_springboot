package com.kq.dao;

import com.kq.pojo.ChatMessage;
import com.kq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatMessageDao extends JpaRepository<ChatMessage,Integer> {
    List<ChatMessage> getChatMessagesByConsultationConsultationId(Integer consultationId);
}
