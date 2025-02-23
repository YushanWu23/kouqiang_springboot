package com.kq.dao;


import com.kq.pojo.Knowledge;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKnowledgeDao extends JpaRepository<Knowledge,Integer> {
    List<Knowledge> findKnowledgesByKnowledgeTitleContaining(String knowledgeTitle);

    @Transactional
    Knowledge findKnowledgeByKnowledgeId(String knowledgeId);

    long countByKnowledgeId(String knowledgeId);
}
