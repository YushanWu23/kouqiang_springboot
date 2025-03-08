package com.kq.controller.forUser;


import com.kq.pojo.forUser.Note;
import com.kq.service.forUser.INoteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/note")
public class NoteController {//记录
    @Resource
    INoteService iNoteService;
    @GetMapping("/getNoteByUserId")//根据用户编号查询记录
    List<Note> getNoteByUserId(@RequestParam String userId){
        return iNoteService.getNoteByUserId(userId);
    }
    @GetMapping("/getNoteByNoteId")
    Note getNoteByNoteId(@RequestParam int noteId){
        return iNoteService.getNoteByNoteId(noteId);
    }
    @PostMapping("/saveNote")
    int saveNote(@RequestParam String noteExplain, @RequestParam(value = "files",required = false) MultipartFile[] files, @RequestParam String userId){
        return iNoteService.saveNote(noteExplain, files, userId);
    }
    @PostMapping("/updateNote")
    int updateNote (@RequestParam int noteId,@RequestParam String retainedImageUrlsJson,@RequestParam(value = "files",required = false) MultipartFile[] files,@RequestParam String noteExplain){
        return iNoteService.updateNote (noteId,retainedImageUrlsJson,files,noteExplain);
    }
    @GetMapping("/removeNote")
    int removeNote(@RequestParam int noteId,@RequestParam String userId){
        return iNoteService.removeNote(noteId,userId);
    }
}
