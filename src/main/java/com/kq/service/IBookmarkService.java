package com.kq.service;

import com.kq.pojo.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookmarkService {
    List<Bookmark> getBookmarkAll(String userId);
    int insertKnowledgeIntoBookmark(String userId,int knowledgeId);
    int deleteBookmark(String userId, int foodId);
}
