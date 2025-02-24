package com.kq.controller;

import com.kq.pojo.Knowledge;
import com.kq.service.IKnowledgeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class KnowledgeController {//科普知识
    @Resource
    IKnowledgeService iKnowledgeService;
    @GetMapping("/getKnowledgeByKnowledgeId")
    public Knowledge getByKnowledgeId(@RequestParam int knowledgeId){
        return iKnowledgeService.getByKnowledgeId(knowledgeId);
    }
    @GetMapping("/getByKnowledgeTitleContaining/{knowledgeTitle}")
    public List<Knowledge> getByKnowledgeTitleContaining(@RequestParam String knowledgeTitle){
        return iKnowledgeService.getByKnowledgeTitleContaining(knowledgeTitle);
    }
    @GetMapping("/getAll")
    List<Knowledge> getAll(){
        return iKnowledgeService.getAll();
    }
    @PostMapping("/getKnowledgeExistence")//查看该知识是否存在
    int getKnowledgeExistence(@RequestParam int knowledgeId){
        return iKnowledgeService.getKnowledgeExistence(knowledgeId);
    }
}
