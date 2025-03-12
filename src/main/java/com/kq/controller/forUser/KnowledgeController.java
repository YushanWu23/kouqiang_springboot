package com.kq.controller.forUser;

import com.kq.pojo.forUser.Knowledge;
import com.kq.service.forUser.IKnowledgeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/knowledge")
public class KnowledgeController {//科普知识
    @Resource
    IKnowledgeService iKnowledgeService;
    @GetMapping("/getKnowledgeByKnowledgeId")
    public Knowledge getByKnowledgeId(@RequestParam int knowledgeId){
        return iKnowledgeService.getByKnowledgeId(knowledgeId);
    }
    @GetMapping("/getByKnowledgeTitleContaining")
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
    @PostMapping("/saveKnowledge")
    int saveKnowledge(@RequestParam String knowledgeExplain, @RequestParam(value = "files",required = false) MultipartFile[] files){
        return iKnowledgeService.saveKnowledge(knowledgeExplain, files);
    }
}
