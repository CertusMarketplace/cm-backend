package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT w FROM WorkEntity w " +
            "LEFT JOIN FETCH w.seller " +
            "LEFT JOIN FETCH w.workCategory " +
            "LEFT JOIN FETCH w.images " +
            "LEFT JOIN FETCH w.ratings " +
            "LEFT JOIN FETCH w.comments c " +
            "LEFT JOIN FETCH c.replies " +
            "WHERE w.workId = :id")
    Optional<WorkEntity> findWorkByIdWithDetails(@Param("id") Long id);
}