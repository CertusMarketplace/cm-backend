package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.WorkModel;

import java.util.List;

public interface ForManagingWork {
    WorkModel satisfyCreateWork( WorkModel workModel );
    List<WorkModel> satisfyFindAllWorkWithRatings();
    WorkModel satisfyFindWorkById( Long id );
    WorkModel satisfyUpdateWork( WorkModel workModel );
    void satisfyDeleteWorkById( Long id );
    List<WorkModel> satisfyFindWorksByIds(List<Long> ids);
}
