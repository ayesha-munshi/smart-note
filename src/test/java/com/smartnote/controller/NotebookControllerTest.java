package com.smartnote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartnote.entity.Notebook;
import com.smartnote.service.NotebookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NotebookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotebookService notebookService;

    @Test
    @DisplayName("GET /notebooks/{notebookId} success")
    public void testGetNotebookSuccess() throws Exception {
        //set up mock
        doReturn(new Notebook(1L,"test", Collections.emptyList())).when(notebookService).findById(1L);

        //test mockmvc get
        this.mockMvc.perform(get("/api/v1/notebooks/1"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /notebooks/{notebookId} success")
    public void testDeleteNotebookSuccess() throws Exception {
        //set up mock
        doNothing().when(notebookService).deleteNotebook(1);

        //test mockmvc delete
        mockMvc.perform(delete("/api/v1/notebooks/1") )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /notebooks success")
    public void testCreateNotebookSuccess() throws Exception {
        //set up mock
        Notebook inputNotebook = new Notebook(1L,"test", Collections.emptyList());
        doReturn(inputNotebook).when(notebookService).createNotebook(inputNotebook);
        String inputNotebookAsJson = new ObjectMapper().writeValueAsString(inputNotebook);

        //test mockmvc post
        mockMvc.perform(post("/api/v1/notebooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNotebookAsJson))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /notebooks/{notebookId} success")
    public void testUpdateNotebookSuccess() throws Exception {
        //set up mock
        doNothing().when(notebookService).updateNotebook(1, "test");

        //test mockmvc put
        mockMvc.perform(put("/api/v1/notebooks/1")
                        .param("notebookDescription", "test updated"))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /notebooks/{notebookId} success")
    public void testGetNotebookByTagSuccess() throws Exception {
        //set up mock
        doReturn(new Notebook(1L,"test", Collections.emptyList())).when(notebookService).findByTag("tag2", 1L);

        //test mockmvc put
        mockMvc.perform(get("/api/v1/notebooks/1")
                        .param("tag", "tag2"))
                .andExpect(status().isOk());
    }

}
