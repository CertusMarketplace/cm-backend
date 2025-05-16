package pe.edu.certus.worksmodule.logic.ports.driver;


import java.util.List;

public interface ForWork <WorkModel, Long>{
    void createWork(WorkModel workModel);
    WorkModel findWorkModelById(Long id);
//    List<WorkModel> findAllWorkModel();
    WorkModel updateWorkModel(WorkModel workModel);
    void deleteWorkModel(Long id);
}
