package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;

import java.util.List;

@Repository
public interface ForQueryingWork extends JpaRepository<WorkEntity, Long> {

    @Query("SELECT DISTINCT w FROM WorkEntity w " +
            "LEFT JOIN FETCH w.workCategory " +
            "LEFT JOIN FETCH w.seller " +
            "LEFT JOIN FETCH w.ratings")
    List<WorkEntity> findAllWithCategoryAndRatings();

    @Query("SELECT w FROM WorkEntity w WHERE w.workId IN :ids")
    List<WorkEntity> findByIdIn(List<Long> ids);

    List<WorkEntity> findAllByIdSellerUserAndWorkIsDeletedFalse(Long idSellerUser);
}