package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.WorkModel;

import java.util.List;

public interface ForManagingWork {
    void satisfyCreateWork( WorkModel workModel );
//    List<WorkModel> satisfyFindAllWork();
    WorkModel satisfyFindWorkById( Long id );
    WorkModel satisfyUpdateWork( WorkModel workModel );
    void satisfyDeleteWorkById( Long id );
}
