package com.smartnote.controller;

import com.smartnote.entity.Notebook;
import com.smartnote.exceptions.ObjectNotFoundException;
import com.smartnote.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @GetMapping("/{notebookId}")
    public Notebook getNotebook(@PathVariable long notebookId) throws ObjectNotFoundException {
        return notebookService.findById(notebookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notebook createNotebook(@RequestBody Notebook notebook) {

        return notebookService.createNotebook(notebook);
    }

    @DeleteMapping("/{notebookId}")
    public void deleteNotebook(@PathVariable long notebookId) throws ObjectNotFoundException {
        notebookService.deleteNotebook(notebookId);
    }

    @PutMapping("/{notebookId}")
    public void updateNotebook(
            @PathVariable long notebookId, @RequestParam(required = false) String noteDescription)
            throws ObjectNotFoundException {
        notebookService.updateNotebook(notebookId, noteDescription);
    }

    @GetMapping(value = "/{notebookId}", params = "tag")
    public Notebook getNotebookByTag(
            @PathVariable long notebookId, @RequestParam String tag) throws ObjectNotFoundException {
        return notebookService.findByTag(tag, notebookId);
    }
}
