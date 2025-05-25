package pe.edu.certus.worksmodule.repository.adapters.mapper;

import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

public class WorkPersistenceMapper implements ForBridgingWork {

    @Override
    public WorkEntity toPersistence( WorkModel workModel ) {
        return WorkEntity.builder()
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
                .workStatus( convertToEntityStatus( workModel.getWorkStatus()) )
                .build();
    }

    @Override
    public  WorkModel fromPersistence( WorkEntity workEntity ) {
        return WorkModel.builder()
                .workId( workEntity.getWorkId() )
                .idSellerUser( workEntity.getIdSellerUser() )
                .idWorkCategory( workEntity.getIdWorkCategory() )
                .workTitle( workEntity.getWorkTitle() )
                .workDescription( workEntity.getWorkDescription() )
                .workPrice( workEntity.getWorkPrice() )
                .workIsDeleted( workEntity.getWorkIsDeleted() )
                .workImageUrl( workEntity.getWorkImageUrl() )
                .workPublishedAt( workEntity.getWorkPublishedAt() )
                .workUpdatedAt( workEntity.getWorkUpdatedAt() )
                .workStatus( convertToModelStatus( workEntity.getWorkStatus()) )
                .build();
    }

    private WorkEntity.WorkStatus convertToEntityStatus(WorkModel.WorkStatus modelStatus) {
        if (modelStatus == null) return null;
        return WorkEntity.WorkStatus.valueOf(modelStatus.name());
    }

    private WorkModel.WorkStatus convertToModelStatus(WorkEntity.WorkStatus entityStatus) {
        if (entityStatus == null) return null;
        return WorkModel.WorkStatus.valueOf(entityStatus.name());
    }
}
