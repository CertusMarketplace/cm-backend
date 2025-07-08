package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.WorkModel;
import java.util.List;

public interface ForManagingWork {
<<<<<<< Updated upstream
    WorkModel satisfyCreateWork( WorkModel workModel );
=======
    void satisfyCreateWork( WorkModel workModel );
>>>>>>> Stashed changes
    List<WorkModel> satisfyFindAllWorkWithRatings();
    WorkModel satisfyFindWorkById( Long id );
    List<WorkModel> satisfyFindWorksBySellerId(Long sellerId);
    WorkModel satisfyUpdateWork( WorkModel workModel );
    void satisfyDeleteWorkById( Long id );
<<<<<<< Updated upstream
    List<WorkModel> satisfyFindWorksByIds(List<Long> ids);
    List<WorkModel> satisfyFindAllBySellerId(Long sellerId);
}
=======
}
>>>>>>> Stashed changes
