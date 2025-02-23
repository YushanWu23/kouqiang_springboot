package com.kq.controller;

import com.kq.pojo.Bookmark;
import com.kq.service.IBookmarkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bookmark")
public class BookmarkController {
    @Resource
    IBookmarkService iBookmarkService;
    @PostMapping("/getBookmarkAll")//显示该收藏夹内容
    List<Bookmark> getBookmarkAll(@RequestParam String userId){
        return iBookmarkService.getBookmarkAll(userId);
    }
    @PostMapping("/insertKnowledgeIntoBookmark")//添加商品到购物车
    int insertKnowledgeIntoBookmark(@RequestParam String userId,@RequestParam int knowledgeId){
        return iBookmarkService.insertKnowledgeIntoBookmark(userId,knowledgeId);
    }
    @PostMapping("/deleteBookmark")
    int deleteBookmark(@RequestParam String userId,@RequestParam int knowledgeId){
        return iBookmarkService.deleteBookmark(userId,knowledgeId);
    }
}
