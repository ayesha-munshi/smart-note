package com.smartnote.service;

import com.smartnote.entity.Notebook;
import com.smartnote.exceptions.ObjectNotFoundException;

public interface NotebookService {

    public Notebook findById(long id) throws ObjectNotFoundException;

    public Notebook createNotebook(Notebook notebookToBeCreated);

    public void deleteNotebook(long notebookId) throws ObjectNotFoundException;

    public void updateNotebook(long notebookId, String description) throws ObjectNotFoundException;
    public Notebook findByTag(String tag, Long notebookId) throws ObjectNotFoundException;

}
