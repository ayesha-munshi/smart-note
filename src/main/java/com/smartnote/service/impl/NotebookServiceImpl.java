package com.smartnote.service.impl;

import com.smartnote.dataaccessobject.NotebookRepository;
import com.smartnote.entity.Note;
import com.smartnote.entity.Notebook;
import com.smartnote.exceptions.ObjectNotFoundException;
import com.smartnote.service.NotebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NotebookServiceImpl implements NotebookService {

    private static final Logger LOG = LoggerFactory.getLogger(NotebookServiceImpl.class);

    @Autowired
    private NotebookRepository notebookRepository;

    public Notebook findById(long notebookId) throws ObjectNotFoundException {
        Optional<Notebook> notebook = notebookRepository.findById(notebookId);
        if (!notebook.isPresent()) {
            LOG.error("ObjectNotFoundException while finding the notebook with id : " + notebookId);
            throw new ObjectNotFoundException("Could not find notebook with id : " + notebookId);
        }
        return notebook.get();
    }

    public Notebook createNotebook(Notebook notebookToBeCreated) {
        notebookToBeCreated.getNotes().forEach(note -> {
            note.setNotebook(notebookToBeCreated);
        });
        notebookRepository.save(notebookToBeCreated);
        return notebookToBeCreated;
    }

    @Transactional
    public void deleteNotebook(long notebookId) throws ObjectNotFoundException {
        Notebook notebookToBeDeleted = findById(notebookId);
        notebookRepository.delete(notebookToBeDeleted);
    }

    @Transactional
    public void updateNotebook(long notebookId, String noteDescription) throws ObjectNotFoundException {
        Notebook notebookToBeUpdated = findById(notebookId);
        notebookToBeUpdated.setNotebookDescription(noteDescription);
    }

    public Notebook findByTag(String tag, Long notebookId) throws ObjectNotFoundException {
        Notebook notebook = findById(notebookId);
        List<Note> noteList = new ArrayList<>();
        notebook.getNotes().forEach(note -> {
            if (Arrays.stream(note.getTag()).anyMatch(tag::equals)) {
                noteList.add(note);
            }
        });
        notebook.setNotes(noteList);

        return notebook;
    }
}
