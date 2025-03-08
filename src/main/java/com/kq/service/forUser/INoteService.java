package com.kq.service.forUser;

import com.kq.pojo.forUser.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface INoteService {
    List<Note> getNoteByUserId(String userId);
    Note getNoteByNoteId( int noteId);
    int saveNote(String noteExplain, MultipartFile[] files,String userId);
    int updateNote ( int noteId, String retainedImageUrlsJson,MultipartFile[] files, String noteExplain);
    int removeNote( int noteId, String userId);
}
