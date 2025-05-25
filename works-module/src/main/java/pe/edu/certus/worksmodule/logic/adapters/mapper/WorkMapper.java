package pe.edu.certus.worksmodule.logic.adapters.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.WorkWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

public class WorkMapper implements ForMappingWork {

    @Override
    public WorkModel fromWeb( WorkWebModel workWebModel ) {
        return WorkModel.builder()
                .workId( workWebModel.workId() )
                .idSellerUser( workWebModel.idSellerUser() )
                .idWorkCategory( workWebModel.idWorkCategory() )
                .workTitle( workWebModel.workTitle() )
                .workDescription( workWebModel.workDescription() )
                .workPrice( workWebModel.workPrice() )
                .workIsDeleted( workWebModel.workIsDeleted() )
                .workImageUrl( workWebModel.workImageUrl() )
                .workPublishedAt( workWebModel.workPublishedAt() )
                .workUpdatedAt( workWebModel.workUpdatedAt() )
                .workStatus( workWebModel.workStatus() )
                .build();    }

    @Override
    public WorkWebModel toWeb( WorkModel workModel ) {
        return new WorkWebModel(
                workModel.getWorkId(),
                workModel.getIdSellerUser(),
                workModel.getIdWorkCategory(),
                workModel.getWorkTitle(),
                workModel.getWorkDescription(),
                workModel.getWorkCategory(),
                workModel.getWorkPrice(),
                workModel.getWorkIsDeleted(),
                workModel.getWorkImageUrl(),
                workModel.getWorkPublishedAt(),
                workModel.getWorkUpdatedAt(),
                workModel.getWorkStatus()
        );
    }
}
