package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.springframework.transaction.annotation.Transactional;
=======
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;
import pe.edu.certus.ratingsmodule.repository.ports.driver.ForQueryingRating;
>>>>>>> Stashed changes
=======
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;
import pe.edu.certus.ratingsmodule.repository.ports.driver.ForQueryingRating;
>>>>>>> Stashed changes
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkImageEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import java.util.List;
import java.util.Map;
>>>>>>> Stashed changes
=======
import java.util.List;
import java.util.Map;
>>>>>>> Stashed changes
import java.util.stream.Collectors;

@Service
@SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
public class WorkQuerierProxy implements ForManagingWork {
    private final ForQueryingWork forQueryingWork;
    private final ForQueryingRating forQueryingRating;
    private final ForBridgingWork forBridgingWork;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public WorkQuerierProxy(ForQueryingWork forQueryingWork, ForBridgingWork forBridgingWork) {
=======
    public WorkQuerierProxy(ForQueryingWork forQueryingWork, ForQueryingRating forQueryingRating, ForBridgingWork forBridgingWork) {
>>>>>>> Stashed changes
=======
    public WorkQuerierProxy(ForQueryingWork forQueryingWork, ForQueryingRating forQueryingRating, ForBridgingWork forBridgingWork) {
>>>>>>> Stashed changes
        this.forQueryingWork = forQueryingWork;
        this.forQueryingRating = forQueryingRating;
        this.forBridgingWork = forBridgingWork;
    }

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Transactional(readOnly = true)
    public List<WorkModel> satisfyFindWorksByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<WorkEntity> workEntities = forQueryingWork.findByIdIn(ids);
=======
    public List<WorkModel> satisfyFindWorksBySellerId(Long sellerId) {
        List<WorkEntity> workEntities = forQueryingWork.findByIdSellerUser(sellerId);
>>>>>>> Stashed changes
=======
    public List<WorkModel> satisfyFindWorksBySellerId(Long sellerId) {
        List<WorkEntity> workEntities = forQueryingWork.findByIdSellerUser(sellerId);
>>>>>>> Stashed changes
        return workEntities.stream()
                .map(forBridgingWork::fromPersistence)
                .collect(Collectors.toList());
    }

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Transactional
    public WorkModel satisfyCreateWork(WorkModel workModel) {
        WorkEntity workEntity = forBridgingWork.toPersistence(workModel);

        workEntity.setWorkStatus(WorkEntity.WorkStatus.EN_REVISION);
        workEntity.setWorkFilePath(workModel.getWorkFilePath());
        workEntity.setWorkIsDeleted(false);
        workEntity.setWorkPublishedAt(LocalDateTime.now());
        workEntity.setWorkUpdatedAt(LocalDateTime.now());

        if (workModel.getImageUrls() != null && !workModel.getImageUrls().isEmpty()) {
            workEntity.setWorkImageUrl(workModel.getImageUrls().get(0));

            Set<WorkImageEntity> imageEntities = new HashSet<>();
            for (int i = 0; i < workModel.getImageUrls().size(); i++) {
                String url = workModel.getImageUrls().get(i);
                WorkImageEntity imageEntity = WorkImageEntity.builder()
                        .work(workEntity)
                        .imageUrl(url)
                        .isPrimary(i == 0)
                        .build();
                imageEntities.add(imageEntity);
            }
            workEntity.setImages(imageEntities);
        }

        WorkEntity savedWorkEntity = forQueryingWork.save(workEntity);

        return forBridgingWork.fromPersistence(savedWorkEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkModel> satisfyFindAllWorkWithRatings() {
        List<WorkEntity> workEntities = forQueryingWork.findAllWithCategoryAndRatings();
        return workEntities.stream()
                .map(forBridgingWork::fromPersistence)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public WorkModel satisfyFindWorkById(Long id) {
        return forQueryingWork.findById(id)
                .map(forBridgingWork::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "THE ENTITY WORK NOT FOUND WITH ID: " + id));
    }

    @Override
    @Transactional
    public WorkModel satisfyUpdateWork(WorkModel workModel) {
        WorkEntity existingWork = forQueryingWork.findById(workModel.getWorkId()).orElseThrow(
                () -> new EntityNotFoundException("Work not found with ID: " + workModel.getWorkId())
        );

        existingWork.setWorkTitle(workModel.getWorkTitle());
        existingWork.setWorkDescription(workModel.getWorkDescription());
        existingWork.setWorkPrice(workModel.getWorkPrice());
        existingWork.setWorkUpdatedAt(LocalDateTime.now());

        WorkEntity updatedEntity = forQueryingWork.save(existingWork);
=======
=======
>>>>>>> Stashed changes
    public List<WorkModel> satisfyFindAllWorkWithRatings() {
        List<WorkEntity> workEntities = forQueryingWork.findAllWithCategory();
        List<RatingEntity> ratingEntities = forQueryingRating.findAll();
        Map<Long, Double> averageRatings = ratingEntities.stream()
                .filter(r -> r.getWorkId() != null)
                .collect(Collectors.groupingBy(
                        RatingEntity::getWorkId,
                        Collectors.averagingDouble(RatingEntity::getRatingScore)
                ));
        return workEntities.stream().map(workEntity -> {
            WorkModel workModel = forBridgingWork.fromPersistence(workEntity);
            double avgRating = averageRatings.getOrDefault(workModel.getWorkId(), 0.0);
            workModel.setAverageRating(avgRating);
            return workModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void satisfyCreateWork(WorkModel workModel) {
        WorkEntity objectFromDomain = forBridgingWork.toPersistence(workModel);
        forQueryingWork.save(objectFromDomain);
    }

    @Override
    public WorkModel satisfyFindWorkById(Long id) {
        return forQueryingWork.findById(id)
                .map(forBridgingWork::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException("Work not found with ID: " + id));
    }

    @Override
    public WorkModel satisfyUpdateWork(WorkModel workModel) {
        WorkEntity objectFromDomain = forBridgingWork.toPersistence(workModel);
        WorkEntity updatedEntity = forQueryingWork.save(objectFromDomain);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        return forBridgingWork.fromPersistence(updatedEntity);
    }

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Transactional
    public void satisfyDeleteWorkById(Long id) {
        if (!forQueryingWork.existsById(id)) {
            throw new EntityNotFoundException("WORK NOT FOUND WITH ID: " + id);
        }
        forQueryingWork.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkModel> satisfyFindAllBySellerId(Long sellerId) {
        List<WorkEntity> workEntities = forQueryingWork.findAllByIdSellerUserAndWorkIsDeletedFalse(sellerId);
        return workEntities.stream()
                .map(forBridgingWork::fromPersistence)
                .collect(Collectors.toList());
    }
=======
    public void satisfyDeleteWorkById(Long id) {
        forQueryingWork.deleteById(id);
    }
>>>>>>> Stashed changes
=======
    public void satisfyDeleteWorkById(Long id) {
        forQueryingWork.deleteById(id);
    }
>>>>>>> Stashed changes
}