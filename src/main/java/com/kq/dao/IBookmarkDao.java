package com.kq.dao;

import com.kq.pojo.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookmarkDao extends JpaRepository<Bookmark,Integer> {
    List<Bookmark> findBookmarksByUserUserId(String userId);

    Bookmark findBookmarkByUserUserIdAndKnowledgeKnowledgeId(String userId,int knowledgeId);
}

