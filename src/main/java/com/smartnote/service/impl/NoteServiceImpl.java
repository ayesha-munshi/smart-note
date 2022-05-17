package com.smartnote.service.impl;

import com.smartnote.entity.Note;
import com.smartnote.entity.Notebook;
import com.smartnote.exceptions.ObjectNotFoundException;
import com.smartnote.service.NoteService;
import com.smartnote.service.NotebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smartnote.dataaccessobject.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger LOG = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NotebookService notebookService;

    public Note findById(long id) throws ObjectNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (!note.isPresent()) {
            LOG.error("ObjectNotFoundException while finding the note with id : " + id);
            throw new ObjectNotFoundException("Could not find note with id : " + id);
        }
        return note.get();
    }

    public List<Note> findAll() {
        return (List<Note>) noteRepository.findAll();
    }

    public Note createNote(Note noteToBeCreated, Long notebookId) throws ObjectNotFoundException {
        Notebook notebook = notebookService.findById(notebookId);
        noteToBeCreated.setNotebook(notebook);
        return noteRepository.save(noteToBeCreated);
    }

    @Transactional
    public void deleteNote(long noteId, Long notebookId) throws ObjectNotFoundException {
        Note noteToBeDeleted = findById(noteId);
        Notebook notebook = notebookService.findById(notebookId);
        notebook.getNotes().remove(noteToBeDeleted);
    }

    @Transactional
    public void updateNote(long noteId, Note note) throws ObjectNotFoundException {
        Note noteToBeUpdated = findById(noteId);
        if (note.getTitle() != null && !note.getTitle().equals(noteToBeUpdated.getTitle())) {
            noteToBeUpdated.setTitle(note.getTitle());
        }
        if (note.getBody() != null && !note.getBody().equals(noteToBeUpdated.getBody())) {
            noteToBeUpdated.setBody(note.getBody());
        }

        if (note.getTag() != null && !note.getTag().equals(noteToBeUpdated.getTag())) {
            noteToBeUpdated.setTag(note.getTag());
        }
    }

    public List<Note> findByTag(String tag) {
        return noteRepository.findByTag(tag);
    }
}
