package com.smartnote.service;

import com.smartnote.dataaccessobject.NoteRepository;
import com.smartnote.entity.Note;
import com.smartnote.entity.Notebook;
import com.smartnote.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private NotebookService notebookService;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws ObjectNotFoundException {
        // Setup our mocks
        Note expectedNote = new Note(1L, "test title1", "test body1", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag1", "tag2"}, new Notebook());
        doReturn(Optional.of(expectedNote)).when(noteRepository).findById(1L);

        // Execute the service call
        Note returnedNote = noteService.findById(1l);

        // verify the response
        verify(noteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test createNote Success")
    void testCreateNote() throws ObjectNotFoundException {
        // Setup our mocks
        Notebook expectedNotebook = new Notebook(1L,"test", Collections.emptyList());
        doReturn(expectedNotebook).when(notebookService).findById(1L);
        Note inputNote = new Note(1L, "test title1", "test body1", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag1", "tag2"}, new Notebook());

        // Execute the service call
        noteService.createNote(inputNote, 1L);

        // verify the response
        verify(noteRepository, times(1)).save(inputNote);
    }

    @Test
    @DisplayName("Test deleteNote Success")
    void testDeleteNote() throws ObjectNotFoundException {
        // Setup our mocks
        Note inputNote = new Note(1L, "test title1", "test body1", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag1", "tag2"}, new Notebook());
        doReturn(Optional.of(inputNote)).when(noteRepository).findById(1L);
        Notebook inputNotebook = new Notebook(1L,"test", new ArrayList<>(Arrays.asList(inputNote)));
        doReturn(inputNotebook).when(notebookService).findById(1L);

        // Execute the service call
        noteService.deleteNote(1, 1L);

        // assert the response
        assertEquals(0, inputNotebook.getNotes().size());
    }

    @Test
    @DisplayName("Test updateNote Success")
    void testUpdateNote() throws ObjectNotFoundException {
        // Setup our mock repository
        Note inputNote = new Note(1L, "test title1", "test body1", LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2), new String[]{"tag1", "tag2"}, new Notebook());
        doReturn(Optional.of(inputNote)).when(noteRepository).findById(1L);
        Note updatedNote = new Note(1L, "test title2", "test body2", LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2), new String[]{"tag1", "tag2"}, new Notebook());

        // Execute the service call
        noteService.updateNote(1, updatedNote);

        // Assert the response
        assertEquals(updatedNote.getTitle(), inputNote.getTitle());
        assertEquals(updatedNote.getBody(), inputNote.getBody());
    }

    @Test
    @DisplayName("Test findByTags Success")
    void testFindByTags() {
        // Setup our mocks
        Note expectedNote = new Note(1L, "test title1", "test body1", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag1", "tag2"}, new Notebook());
        doReturn(Arrays.asList(expectedNote)).when(noteRepository).findByTag("tag2");

        // Execute the service call
        List<Note> returnedNote = noteService.findByTag("tag2");

        // verify the response
        verify(noteRepository, times(1)).findByTag("tag2");
    }

}
