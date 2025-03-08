package com.kq.dao.forUser;


import com.kq.pojo.forUser.Knowledge;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKnowledgeDao extends JpaRepository<Knowledge,Integer> {
    List<Knowledge> findKnowledgesByKnowledgeTitleContaining(String knowledgeTitle);

    @Transactional
    Knowledge findKnowledgeByKnowledgeId(int knowledgeId);

    long countByKnowledgeId(int knowledgeId);
}
