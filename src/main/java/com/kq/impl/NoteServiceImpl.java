package com.kq.impl;


import com.kq.dao.INoteDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.Note;
import com.kq.pojo.User;
import com.kq.service.INoteService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceImpl implements INoteService {
    @Resource
    INoteDao iNoteDao;
    @Resource
    IUserDao iUserDao;
    @Override
    public List<Note> getNoteByUserId(String userId){
        Sort sort = Sort.by(Sort.Direction.DESC, "noteId");//降序
        return iNoteDao.findNotesByUserUserId(userId,sort);
    }
    @Override
    public int saveNote( String noteExplain,String userId){
        Note note = new Note();
        note.setNoteExplain(noteExplain);
        User user = iUserDao.findUserByUserId(userId);
        note.setUser(user);
        iNoteDao.save(note);
        return 1;
    }
    @Override
    public int updateNote ( int noteId, String noteExplain){
        Note note = iNoteDao.findNoteByNoteId(noteId);
        note.setNoteExplain(noteExplain);
        iNoteDao.save(note);
        return 1;
    }
    @Override
    public int removeNote( int noteId, String userId){
        Note note = iNoteDao.findNoteByNoteId(noteId);
        User user = iUserDao.findUserByUserId(userId);
        user.removeNote(note);
        iUserDao.save(user);
        return 1;
    }
}

