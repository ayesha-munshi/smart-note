package com.smartnote.service;

import com.smartnote.dataaccessobject.NotebookRepository;
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
public class NotebookServiceTest {

    @Autowired
    private NotebookService notebookService;

    @MockBean
    private NotebookRepository notebookRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws ObjectNotFoundException {
        // Setup our mocks
        Notebook expectedNotebook = new Notebook(1L,"test", Collections.emptyList());
        doReturn(Optional.of(expectedNotebook)).when(notebookRepository).findById(1L);

        // Execute the service call
        Notebook returnedNotebook = notebookService.findById(1l);

        // verify the response
        assertNotNull(returnedNotebook);
        verify(notebookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test createNotebook Success")
    void testCreateNotebook() {
        // Setup our mocks
        Notebook inputNotebook = new Notebook(1L,"test", Collections.emptyList());
        // Execute the service call
        notebookService.createNotebook(inputNotebook);

        // verify the response
        verify(notebookRepository, times(1)).save(inputNotebook);
    }

    @Test
    @DisplayName("Test deleteNotebook Success")
    void testDeleteNotebook() throws ObjectNotFoundException {
        // Setup our mocks
        Notebook inputNotebook = new Notebook(1L,"test", Collections.emptyList());
        doReturn(Optional.of(inputNotebook)).when(notebookRepository).findById(1L);

        // Execute the service call
        notebookService.deleteNotebook(1);

        // verify the response
        verify(notebookRepository, times(1)).delete(inputNotebook);
    }

    @Test
    @DisplayName("Test updateNotebook Success")
    void testUpdateNotebook() throws ObjectNotFoundException {
        // Setup our mocks
        Notebook inputNotebook = new Notebook(1L,"test", Collections.emptyList());
        doReturn(Optional.of(inputNotebook)).when(notebookRepository).findById(1L);

        // Execute the service call
        notebookService.updateNotebook(1, "test description");

        // Assert the response
        assertEquals("test description", inputNotebook.getNotebookDescription());
    }

    @Test
    @DisplayName("Test findByTag Success")
    void testFindByTag() throws ObjectNotFoundException {
        // Setup our mocks
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(1L, "test title1", "test body1", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag1", "tag2"}, new Notebook()));
        noteList.add(new Note(2L, "test title2", "test body2", LocalDateTime.now(), LocalDateTime.now(), new String[]{"tag11", "tag12"}, new Notebook()));

        Notebook inputNotebook = new Notebook(1L,"test", noteList);

        doReturn(Optional.of(inputNotebook)).when(notebookRepository).findById(1L);

        // Execute the service call
        Notebook outputNotebook = notebookService.findByTag("tag12", 1L);

        // Assert the response
        int outputTagSize = outputNotebook.getNotes().size();
        assertEquals(1, outputTagSize);
        assertEquals(2L, outputNotebook.getNotes().get(0).getId());
    }
}
