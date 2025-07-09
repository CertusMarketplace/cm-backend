package pe.edu.certus.worksmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.repository.entity.RatingEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkImageEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingComment;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingRating;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkPersistenceMapper implements ForBridgingWork {

    private final ForBridgingRating forBridgingRating;
    private final ForBridgingComment forBridgingComment;

    public WorkPersistenceMapper(ForBridgingRating forBridgingRating, ForBridgingComment forBridgingComment) {
        this.forBridgingRating = forBridgingRating;
        this.forBridgingComment = forBridgingComment;
    }

    @Override
    public WorkModel fromPersistence(WorkEntity workEntity) {
        if (workEntity == null) {
            return null;
        }

        String sellerFullName = "Vendedor Anónimo";
        if (workEntity.getSeller() != null && workEntity.getSeller().getPersonName() != null) {
            sellerFullName = workEntity.getSeller().getPersonName() + " " + workEntity.getSeller().getPersonLastname();
        }

        Double averageRating = workEntity.getRatings() != null ?
                workEntity.getRatings().stream()
                        .mapToDouble(RatingEntity::getRatingScore)
                        .average()
                        .orElse(0.0) : 0.0;

        Set<RatingModel> ratingModels = workEntity.getRatings() != null ?
                workEntity.getRatings().stream()
                        .map(forBridgingRating::fromPersistence)
                        .collect(Collectors.toSet()) : // CORREGIDO
                Collections.emptySet();

        Set<CommentModel> commentModels = workEntity.getComments() != null ?
                workEntity.getComments().stream()
                        .map(forBridgingComment::fromPersistence)
                        .collect(Collectors.toSet()) : // CORREGIDO
                Collections.emptySet();

        Long categoryId = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getId() : null;
        String categoryName = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getName() : null;

        List<String> imageUrls = workEntity.getImages() != null ?
                workEntity.getImages().stream()
                        .sorted(Comparator.comparing(WorkImageEntity::getIsPrimary).reversed().thenComparing(WorkImageEntity::getImageId))
                        .map(WorkImageEntity::getImageUrl)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return WorkModel.builder()
                .workId(workEntity.getWorkId())
                .idSellerUser(workEntity.getIdSellerUser())
                .sellerName(sellerFullName)
                .idWorkCategory(categoryId)
                .workCategory(categoryName)
                .workTitle(workEntity.getWorkTitle())
                .workDescription(workEntity.getWorkDescription())
                .workPrice(workEntity.getWorkPrice())
                .workIsDeleted(workEntity.getWorkIsDeleted())
                .workImageUrl(imageUrls.isEmpty() ? workEntity.getWorkImageUrl() : imageUrls.get(0))
                .imageUrls(imageUrls)
                .workFilePath(workEntity.getWorkFilePath())
                .workPublishedAt(workEntity.getWorkPublishedAt())
                .workUpdatedAt(workEntity.getWorkUpdatedAt())
                .workStatus(convertToModelStatus(workEntity.getWorkStatus()))
                .averageRating(averageRating)
                .ratings(ratingModels)
                .comments(commentModels)
                .build();
    }

    @Override
    public WorkEntity toPersistence(WorkModel workModel) {
        WorkEntity workEntity = WorkEntity.builder()
                .idSellerUser(workModel.getIdSellerUser())
                .workTitle(workModel.getWorkTitle())
                .workDescription(workModel.getWorkDescription())
                .workPrice(workModel.getWorkPrice())
                .build();

        if (workModel.getIdWorkCategory() != null) {
            workEntity.setWorkCategory(new pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity());
            workEntity.getWorkCategory().setId(workModel.getIdWorkCategory());
        }

        return workEntity;
    }

    private WorkModel.WorkStatus convertToModelStatus(WorkEntity.WorkStatus entityStatus) {
        if (entityStatus == null) return null;
        return WorkModel.WorkStatus.valueOf(entityStatus.name());
    }
}