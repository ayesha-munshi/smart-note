package com.smartnote.dataaccessobject;

import com.smartnote.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

}

