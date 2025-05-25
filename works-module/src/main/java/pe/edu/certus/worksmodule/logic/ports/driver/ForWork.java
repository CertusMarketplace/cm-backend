package pe.edu.certus.worksmodule.logic.ports.driver;


import java.util.List;

public interface ForWork <WorkModel, Long>{
    void createWork(WorkModel workModel);
    WorkModel findWorkById(Long id);
    List<WorkModel> findAllWorks();
    WorkModel updateWork(WorkModel workModel);
    void deleteWork(Long id);
}
