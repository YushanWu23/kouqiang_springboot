package com.kq.controller;


import com.kq.pojo.Note;
import com.kq.service.INoteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/saveNote")
    int saveNote(@RequestParam String noteExplain,@RequestParam String userId){
        return iNoteService.saveNote(noteExplain, userId);
    }
    @PostMapping("/updateNote")
    int updateNote (@RequestParam int noteId,@RequestParam String noteExplain){
        return iNoteService.updateNote (noteId,noteExplain);
    }
    @GetMapping("/removeNote")
    int removeNote(@RequestParam int noteId,@RequestParam String userId){
        return iNoteService.removeNote(noteId,userId);
    }
}
