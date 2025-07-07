package pe.edu.certus.worksmodule.repository.ports.mapper;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;

@Service
public interface ForBridgingWork {

    WorkEntity toPersistence( WorkModel workModel );

    WorkModel fromPersistence( WorkEntity workEntity );

}
