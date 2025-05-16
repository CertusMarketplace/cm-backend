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
        return WorkWebModel.builder ()
                .workId( workModel.getWorkId() )
                .idSellerUser( workModel.getIdSellerUser() )
                .idWorkCategory( workModel.getIdWorkCategory() )
                .workTitle( workModel.getWorkTitle() )
                .workDescription( workModel.getWorkDescription() )
                .workPrice( workModel.getWorkPrice() )
                .workIsDeleted( workModel.getWorkIsDeleted() )
                .workImageUrl( workModel.getWorkImageUrl() )
                .workPublishedAt( workModel.getWorkPublishedAt() )
                .workUpdatedAt( workModel.getWorkUpdatedAt() )
                .workStatus( workModel.getWorkStatus() )
                .build();    }
}
