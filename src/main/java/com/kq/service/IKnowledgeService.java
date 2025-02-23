package com.kq.service;

import com.kq.pojo.Knowledge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IKnowledgeService {
    List<Knowledge> getByKnowledgeTitleContaining(String knowledgeTitle);
    List<Knowledge> getAll();
    Knowledge getByKnowledgeId(String knowledgeId);
    int getKnowledgeExistence(String knowledgeId);
}
