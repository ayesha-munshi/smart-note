package com.smartnote;

import com.smartnote.controller.NoteController;
import com.smartnote.controller.NotebookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmartNoteApplicationTest {
    @Autowired
    private NotebookController notebookController;

    @Autowired
    private NoteController noteController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(notebookController).isNotNull();
        assertThat(noteController).isNotNull();
    }
}
