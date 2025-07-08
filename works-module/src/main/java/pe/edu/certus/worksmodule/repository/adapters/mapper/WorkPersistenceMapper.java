package pe.edu.certus.worksmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import pe.edu.certus.worksmodule.repository.entity.RatingEntity;
=======
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;
>>>>>>> Stashed changes
=======
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;
>>>>>>> Stashed changes
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkImageEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingComment;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingRating;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkPersistenceMapper implements ForBridgingWork {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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

        List<RatingModel> ratingModels = workEntity.getRatings() != null ?
                workEntity.getRatings().stream()
                        .map(forBridgingRating::fromPersistence)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        List<CommentModel> commentModels = workEntity.getComments() != null ?
                workEntity.getComments().stream()
                        .map(forBridgingComment::fromPersistence)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        Long categoryId = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getId() : null;
        String categoryName = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getName() : null;

        // CAMBIO: Leer la colección de entidades y extraer las URLs.
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
=======
    @Override
    public WorkEntity toPersistence(WorkModel workModel) {
        WorkCategoryEntity categoryEntity = null;
        if (workModel.getIdWorkCategory() != null) {
            categoryEntity = new WorkCategoryEntity();
            categoryEntity.setId(workModel.getIdWorkCategory());
        }

        return WorkEntity.builder()
                .workId(workModel.getWorkId())
                .idSellerUser(workModel.getIdSellerUser())
                .workCategory(categoryEntity)
                .workTitle(workModel.getWorkTitle())
                .workDescription(workModel.getWorkDescription())
                .workPrice(workModel.getWorkPrice())
                .workIsDeleted(workModel.getWorkIsDeleted())
                .workImageUrl(workModel.getWorkImageUrl())
                .workPublishedAt(workModel.getWorkPublishedAt())
                .workUpdatedAt(workModel.getWorkUpdatedAt())
                .workStatus(convertToEntityStatus(workModel.getWorkStatus()))
=======
    @Override
    public WorkEntity toPersistence(WorkModel workModel) {
        WorkCategoryEntity categoryEntity = null;
        if (workModel.getIdWorkCategory() != null) {
            categoryEntity = new WorkCategoryEntity();
            categoryEntity.setId(workModel.getIdWorkCategory());
        }

        return WorkEntity.builder()
                .workId(workModel.getWorkId())
                .idSellerUser(workModel.getIdSellerUser())
                .workCategory(categoryEntity)
                .workTitle(workModel.getWorkTitle())
                .workDescription(workModel.getWorkDescription())
                .workPrice(workModel.getWorkPrice())
                .workIsDeleted(workModel.getWorkIsDeleted())
                .workImageUrl(workModel.getWorkImageUrl())
                .workPublishedAt(workModel.getWorkPublishedAt())
                .workUpdatedAt(workModel.getWorkUpdatedAt())
                .workStatus(convertToEntityStatus(workModel.getWorkStatus()))
                .build();
    }

    @Override
    public  WorkModel fromPersistence(WorkEntity workEntity) {
        Long categoryId = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getId() : null;
        String categoryName = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getName() : null;

        return WorkModel.builder()
                .workId(workEntity.getWorkId())
                .idSellerUser(workEntity.getIdSellerUser())
                .idWorkCategory(categoryId)
                .workCategory(categoryName)
                .workTitle(workEntity.getWorkTitle())
                .workDescription(workEntity.getWorkDescription())
                .workPrice(workEntity.getWorkPrice())
                .workIsDeleted(workEntity.getWorkIsDeleted())
                .workImageUrl(workEntity.getWorkImageUrl())
                .workPublishedAt(workEntity.getWorkPublishedAt())
                .workUpdatedAt(workEntity.getWorkUpdatedAt())
                .workStatus(convertToModelStatus(workEntity.getWorkStatus()))
>>>>>>> Stashed changes
                .build();
    }

    @Override
    public  WorkModel fromPersistence(WorkEntity workEntity) {
        Long categoryId = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getId() : null;
        String categoryName = workEntity.getWorkCategory() != null ? workEntity.getWorkCategory().getName() : null;

        return WorkModel.builder()
                .workId(workEntity.getWorkId())
                .idSellerUser(workEntity.getIdSellerUser())
>>>>>>> Stashed changes
                .idWorkCategory(categoryId)
                .workCategory(categoryName)
                .workTitle(workEntity.getWorkTitle())
                .workDescription(workEntity.getWorkDescription())
                .workPrice(workEntity.getWorkPrice())
                .workIsDeleted(workEntity.getWorkIsDeleted())
                .workImageUrl(workEntity.getWorkImageUrl())
<<<<<<< Updated upstream
                .imageUrls(imageUrls) // Se asigna la lista completa
                .workFilePath(workEntity.getWorkFilePath())
                .workPublishedAt(workEntity.getWorkPublishedAt())
                .workUpdatedAt(workEntity.getWorkUpdatedAt())
                .workStatus(convertToModelStatus(workEntity.getWorkStatus()))
                .averageRating(averageRating)
                .ratings(ratingModels)
                .comments(commentModels)
=======
                .workPublishedAt(workEntity.getWorkPublishedAt())
                .workUpdatedAt(workEntity.getWorkUpdatedAt())
                .workStatus(convertToModelStatus(workEntity.getWorkStatus()))
>>>>>>> Stashed changes
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