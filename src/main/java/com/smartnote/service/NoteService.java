package com.smartnote.service;

import com.smartnote.entity.Note;
import com.smartnote.exceptions.ObjectNotFoundException;

import java.util.List;

public interface NoteService {

    public Note findById(long id) throws ObjectNotFoundException;

    public List<Note> findAll();

    public Note createNote(Note noteToBeCreated, Long notebookId) throws ObjectNotFoundException;

    public void deleteNote(long noteId, Long notebookId) throws ObjectNotFoundException;

    public void updateNote(long noteId, Note note) throws ObjectNotFoundException;

    public List<Note> findByTag(String tag);

}
