package com.kq.impl;

import com.kq.dao.IKnowledgeDao;
import com.kq.pojo.Knowledge;
import com.kq.service.IKnowledgeService;
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

