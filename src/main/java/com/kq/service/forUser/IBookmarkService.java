package com.kq.service.forUser;

import com.kq.pojo.forUser.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookmarkService {
    List<Bookmark> getBookmarkAll(String userId);
    int insertKnowledgeIntoBookmark(String userId,int knowledgeId);
    int deleteBookmark(String userId, int knowledgeId);
    boolean isBookmarked( String userId, int knowledgeId);
}
