package com.kq.impl.forUser;

import com.kq.dao.forUser.IKnowledgeDao;
import com.kq.pojo.forUser.Knowledge;
import com.kq.service.forUser.IKnowledgeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnowledgeServiceImpl implements IKnowledgeService {
    @Resource
    IKnowledgeDao iKnowledgeDao;
    @Override
    public Knowledge getByKnowledgeId(int knowledgeId){
        return iKnowledgeDao.findKnowledgeByKnowledgeId(knowledgeId);
    }
    @Override
    public List<Knowledge> getByKnowledgeTitleContaining(String knowledgeTitle){
        return  iKnowledgeDao.findKnowledgesByKnowledgeTitleContaining(knowledgeTitle);
    }
    @Override
    public List<Knowledge> getAll(){
        return iKnowledgeDao.findAll();
    }
    @Override//根据编号查询表返回的行数
    public int getKnowledgeExistence(int knowledgeId) {
        long count = iKnowledgeDao.countByKnowledgeId(knowledgeId);
        return (int) count;
    }
}

