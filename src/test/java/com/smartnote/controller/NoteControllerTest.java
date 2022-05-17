package com.smartnote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartnote.entity.Note;
import com.smartnote.entity.Notebook;
import com.smartnote.service.NoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    @DisplayName("GET /notes/{noteId} success")
    public void testGetNoteSuccess() throws Exception {
        //set up mock
        doReturn(new Note(1L,"test", "test body", LocalDateTime.now(), LocalDateTime.now(), new String []{"tag1", "tag"}, new Notebook())).when(noteService).findById(1L);

        //test mockmvc get
        this.mockMvc.perform(get("/api/v1/notes/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("DELETE /notebooks/{notebookId}/notes/{noteId} success")
    public void testDeleteNoteSuccess() throws Exception {
        //set up mock
        doNothing().when(noteService).deleteNote(1L, 1L);

        //test mockmvc delete
        mockMvc.perform(delete("/api/v1/notebooks/1/notes/1") )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /notebooks/{notebookId}/notes success")
    public void testCreateNoteSuccess() throws Exception {
        //set up mock
        Note inputNote = new Note(1L,"test", "test body", LocalDateTime.now(), LocalDateTime.now(), new String []{"tag1", "tag"}, new Notebook());
        doReturn(inputNote).when(noteService).createNote(inputNote, 1L);
        String inputNoteAsJson = new ObjectMapper().findAndRegisterModules().writeValueAsString(inputNote);

        //test mockmvc post
        mockMvc.perform(post("/api/v1/notebooks/1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNoteAsJson))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("PUT /notes/{noteId} success")
    public void testUpdateNoteSuccess() throws Exception {
        //set up mock
        Note inputNote = new Note(1L,"test", "test body", LocalDateTime.now(), LocalDateTime.now(), new String []{"tag1", "tag"}, new Notebook());
        String inputNoteAsJson = new ObjectMapper().findAndRegisterModules().writeValueAsString(inputNote);
        doNothing().when(noteService).updateNote(1, inputNote);

        //test mockmvc put
        mockMvc.perform(put("/api/v1/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputNoteAsJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /notes success")
    public void testGetNoteByTagSuccess() throws Exception {
        //set up mock
        doReturn(Arrays.asList(new Note(1L,"test", "test body", LocalDateTime.now(), LocalDateTime.now(), new String []{"tag1", "tag2"}, new Notebook()))).when(noteService).findByTag("tag2");

        //test mockmvc put
        mockMvc.perform(get("/api/v1/notes")
                        .param("tag", "tag2"))
                .andExpect(status().isOk());
    }
}
