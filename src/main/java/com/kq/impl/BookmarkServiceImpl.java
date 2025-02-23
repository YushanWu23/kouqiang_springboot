package com.kq.impl;


import com.kq.dao.IBookmarkDao;
import com.kq.dao.IKnowledgeDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.Bookmark;
import com.kq.pojo.Knowledge;
import com.kq.pojo.User;
import com.kq.service.IBookmarkService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookmarkServiceImpl implements IBookmarkService {
    @Resource
    IBookmarkDao iBookmarkDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IKnowledgeDao iKnowledgeDao;
    @Override
    @Transactional
    public List<Bookmark> getBookmarkAll(String userId){
        return iBookmarkDao.findBookmarksByUserUserId(userId);
    }
    @Override
    public int insertKnowledgeIntoBookmark(String userId, int knowledgeId){
        Bookmark existingBookmark = iBookmarkDao.findBookmarkByUserUserIdAndKnowledgeKnowledgeId(userId, knowledgeId);
        if (existingBookmark != null) {
            return 1;
        } else {
            Bookmark bookmark = new Bookmark();
            Knowledge knowledge = iKnowledgeDao.findById(knowledgeId).orElse(null);
            // 处理不存在的情况，例如抛出异常或返回错误码
            if (knowledge == null) {
                return -1;
            }
            bookmark.setKnowledge(knowledge);
            User user = iUserDao.findById(userId).orElse(null);
            if (user == null) {
                return -1;
            }
            bookmark.setUser(user);
            iBookmarkDao.save(bookmark);
        }
        return 1;// 添加商品到购物车成功
    }
    @Override
    public int deleteBookmark( String userId, int knowledgeId){
        Bookmark bookmark = iBookmarkDao.findBookmarkByUserUserIdAndKnowledgeKnowledgeId(userId,knowledgeId);
        iBookmarkDao.delete(bookmark);
        return 1;
    }
}

