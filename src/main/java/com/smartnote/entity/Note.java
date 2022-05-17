package com.smartnote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTE")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @Column(name = "note_title")
    private String title;

    @Column(name = "note_body")
    private String body;

    @Column(name = "note_created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "note_modified_time")
    @UpdateTimestamp
    private LocalDateTime modifiedTime;

    @ElementCollection
    @OrderColumn(name = "tag_id")
    @CollectionTable(name = "note_tag", joinColumns=@JoinColumn(name = "note_id", referencedColumnName = "note_id"))
    private String [] tag;

    @ManyToOne
    @JoinColumn(name="notebook_id", referencedColumnName = "notebook_id")
    @JsonIgnoreProperties("notes")
    private Notebook notebook;

    public Note() {
    }

    public Note(Long id, String title, String body, LocalDateTime createdTime, LocalDateTime modifiedTime, String[] tag, Notebook notebook) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.tag = tag;
        this.notebook = notebook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }
}
