package com.kq.impl.forUser;

import com.kq.dao.forUser.IKnowledgeDao;
import com.kq.pojo.forUser.Knowledge;
import com.kq.service.forUser.IKnowledgeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Override
    public int saveKnowledge( String knowledgeExplain,  MultipartFile[] files){
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeExplain(knowledgeExplain);
        List<String> imageUrls = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 确保上传目录存在
                        Path uploadDir = Paths.get("D:\\kouqiang-uploadImg");
                        if (!Files.exists(uploadDir)) {
                            Files.createDirectories(uploadDir);
                        }

                        // 生成唯一的文件名
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadDir.resolve(fileName);

                        // 保存文件
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // 生成图片访问 URL
                        String imageUrl = "/uploads/" + fileName;
                        imageUrls.add(imageUrl);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store image file", e);
                    }
                }
            }
        }
        knowledge.setImageUrls(imageUrls);
        iKnowledgeDao.save(knowledge);
        return 1;
    }
}

