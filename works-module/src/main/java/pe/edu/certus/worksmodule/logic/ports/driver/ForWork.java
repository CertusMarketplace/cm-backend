package pe.edu.certus.worksmodule.logic.ports.driver;

import java.util.List;

public interface ForWork <WorkModel, Long>{
    WorkModel createWork( WorkModel workModel);
    WorkModel findWorkById(Long id);
    List<WorkModel> findAllWorks(String category, String priceRange, Integer popularity);
    WorkModel updateWork(WorkModel workModel);
    void deleteWork(Long id);
    List<WorkModel> findWorksByIds(List<Long> ids);
    List<WorkModel> findAllBySellerId(Long sellerId);
}