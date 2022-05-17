package com.smartnote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NOTEBOOK")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebook_id")
    private Long notebookId;

    @Column(name = "notebook_description")
    private String notebookDescription;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notebook", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("notebook")
    private List<Note> notes = new ArrayList<>();

    public Notebook() {
    }

    public Notebook(Long notebookId, String notebookDescription, List<Note> notes) {
        this.notebookId = notebookId;
        this.notebookDescription = notebookDescription;
        this.notes = notes;
    }

    public Long getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }

    public String getNotebookDescription() {
        return notebookDescription;
    }

    public void setNotebookDescription(String notebookDescription) {
        this.notebookDescription = notebookDescription;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
