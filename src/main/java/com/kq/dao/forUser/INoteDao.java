package com.kq.dao.forUser;

import com.kq.pojo.forUser.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteDao extends JpaRepository<Note,Integer> {
    List<Note> findNotesByUserUserId(String userId, Sort sort);
    Note findNoteByNoteId(int noteId);
}
