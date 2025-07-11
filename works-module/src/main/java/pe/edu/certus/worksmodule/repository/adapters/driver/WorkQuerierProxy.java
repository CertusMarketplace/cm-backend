package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkImageEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkQuerierProxy implements ForManagingWork {
    private final ForQueryingWork forQueryingWork;
    private final ForBridgingWork forBridgingWork;

    public WorkQuerierProxy(ForQueryingWork forQueryingWork, ForBridgingWork forBridgingWork) {
        this.forQueryingWork = forQueryingWork;
        this.forBridgingWork = forBridgingWork;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkModel> satisfyFindWorksByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<WorkEntity> workEntities = forQueryingWork.findByIdIn(ids);
        return workEntities.stream()
                .map(forBridgingWork::fromPersistence)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WorkModel satisfyCreateWork(WorkModel workModel) {
        WorkEntity workEntity = forBridgingWork.toPersistence(workModel);

        workEntity.setWorkStatus(WorkEntity.WorkStatus.PUBLICADO);
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
        return forQueryingWork.findWorkByIdWithDetails(id)
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
        return forBridgingWork.fromPersistence(updatedEntity);
    }

    @Override
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
}