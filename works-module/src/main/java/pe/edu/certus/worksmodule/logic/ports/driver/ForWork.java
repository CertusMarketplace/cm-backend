package pe.edu.certus.worksmodule.logic.ports.driver;

<<<<<<< Updated upstream
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
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import java.util.List;

public interface ForWork {
    void createWork(WorkModel workModel);
    WorkModel findWorkById(Long id);
    List<WorkModel> findWorksBySellerId(Long sellerId);
    Page<WorkModel> findAllWorks(String category, String priceRange, Integer popularity, Pageable pageable);
    WorkModel updateWork(WorkModel workModel);
    void deleteWork(Long id);
}
>>>>>>> Stashed changes
