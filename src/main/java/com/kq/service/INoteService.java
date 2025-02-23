package com.kq.service;

import com.kq.pojo.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoteService {
    List<Note> getNoteByUserId(String userId);
    int saveNote( String noteExplain,String userId);
    int updateNote ( int noteId, String noteExplain);
    int removeNote( int noteId, String userId);
}
