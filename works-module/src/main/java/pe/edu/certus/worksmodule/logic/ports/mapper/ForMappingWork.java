package pe.edu.certus.worksmodule.logic.ports.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;

public interface ForMappingWork {

    WorkModel fromWeb( WorkWebModel workWebModel );

    WorkWebModel toWeb( WorkModel workModel );

}
