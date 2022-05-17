package com.smartnote.controller;

import com.smartnote.entity.Note;
import com.smartnote.exceptions.ObjectNotFoundException;
import com.smartnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes/{noteId}")
    public Note getNoteById(@PathVariable long noteId) throws ObjectNotFoundException {
        return noteService.findById(noteId);
    }

    @PostMapping("/notebooks/{notebookId}/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@PathVariable(value = "notebookId") Long notebookId, @RequestBody Note note) throws ObjectNotFoundException {
        return noteService.createNote(note, notebookId);
    }

    @DeleteMapping("/notebooks/{notebookId}/notes/{noteId}")
    public void deleteNote(@PathVariable(value = "notebookId") Long notebookId, @PathVariable Long noteId) throws ObjectNotFoundException {
        noteService.deleteNote(noteId, notebookId);
    }

    @PutMapping("/notes/{noteId}")
    public void updateNote(@PathVariable long noteId, @RequestBody Note note) throws ObjectNotFoundException {
        noteService.updateNote(noteId, note);
    }

    @GetMapping("/notes")
    public List<Note> getNoteByTag(@RequestParam String tag) {
        return noteService.findByTag(tag);
    }

}
