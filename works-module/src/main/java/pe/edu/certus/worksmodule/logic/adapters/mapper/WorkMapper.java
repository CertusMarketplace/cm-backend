package pe.edu.certus.worksmodule.logic.adapters.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingComment;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingRating;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

import java.util.Collections;
import java.util.stream.Collectors;

public class WorkMapper implements ForMappingWork {

    private final ForMappingRating forMappingRating;
    private final ForMappingComment forMappingComment;

    public WorkMapper(ForMappingRating forMappingRating, ForMappingComment forMappingComment) {
        this.forMappingRating = forMappingRating;
        this.forMappingComment = forMappingComment;
    }

    @Override
    public WorkModel fromWeb(WorkWebModel workWebModel) {
        return WorkModel.builder()
                .workId(workWebModel.workId())
                .idSellerUser(workWebModel.idSellerUser())
                .idWorkCategory(workWebModel.idWorkCategory())
                .workTitle(workWebModel.workTitle())
                .workDescription(workWebModel.workDescription())
                .workPrice(workWebModel.workPrice())
                .workImageUrl(workWebModel.workImageUrl())
                .imageUrls(workWebModel.imageUrls())
                .workStatus(workWebModel.workStatus())
                .build();
    }

    @Override
    public WorkWebModel toWeb(WorkModel workModel) {
        return new WorkWebModel(
                workModel.getWorkId(),
                workModel.getIdSellerUser(),
                workModel.getIdWorkCategory(),
                workModel.getWorkTitle(),
                workModel.getWorkDescription(),
                workModel.getWorkPrice(),
                workModel.getWorkImageUrl(),
                workModel.getImageUrls(),
                workModel.getWorkStatus(),
                workModel.getWorkIsDeleted(),
                workModel.getWorkPublishedAt(),
                workModel.getWorkUpdatedAt(),
                workModel.getWorkCategory(),
                workModel.getSellerName(),
                workModel.getAverageRating(),
                workModel.getRatings() != null ? workModel.getRatings().stream().map(forMappingRating::toWeb).collect(Collectors.toList()) : Collections.emptyList(),
                workModel.getComments() != null ? workModel.getComments().stream().map(forMappingComment::toWeb).collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}