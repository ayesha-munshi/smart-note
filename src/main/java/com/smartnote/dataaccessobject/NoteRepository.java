package com.smartnote.dataaccessobject;

import com.smartnote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n JOIN n.tag t WHERE t = :tag")
    List<Note> findByTag(String tag);
}

